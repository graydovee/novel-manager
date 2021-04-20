package cn.graydove.ndovel.spider.service.impl;

import cn.graydove.ndovel.common.response.Response;
import cn.graydove.ndovel.server.api.facade.NovelFacade;
import cn.graydove.ndovel.server.api.model.request.NovelPutRequest;
import cn.graydove.ndovel.spider.config.Tenant;
import cn.graydove.ndovel.spider.config.TenantManager;
import cn.graydove.ndovel.spider.model.dto.BookDTO;
import cn.graydove.ndovel.spider.model.dto.BroadCastDTO;
import cn.graydove.ndovel.spider.model.dto.NovelPutDTO;
import cn.graydove.ndovel.spider.service.SpiderService;
import cn.graydove.ndovel.server.api.facade.BookWriteFacade;
import cn.graydove.ndovel.server.api.enums.PublishStatus;
import cn.graydove.ndovel.server.api.model.request.BookRequest;
import cn.graydove.ndovel.server.api.model.request.ChapterRequest;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author graydove
 */
@Slf4j
@Service
public class SpiderServiceImpl implements SpiderService {

    private static final String PUBLISH_MESSAGE_UUID = "ndovel:publish:broadcast:";

    @DubboReference
    private BookWriteFacade bookWriteFacade;

    @DubboReference
    private NovelFacade novelFacade;

    private TenantManager tenantManager;

    private RestTemplate restTemplate;

    private StringRedisTemplate stringRedisTemplate;

    public SpiderServiceImpl(TenantManager tenantManager, RestTemplate restTemplate) {
        this.tenantManager = tenantManager;
        this.restTemplate = restTemplate;
    }

    @Override
    public void spider(BookDTO bookDTO) {
        BookRequest bookRequest = BeanUtil.toBean(bookDTO, BookRequest.class);
        bookRequest.setStatus(PublishStatus.RELEASE);
        Long bookId = bookWriteFacade.createBook(bookRequest);

        for (int i=0; i < 25; ++i) {
            ChapterRequest chapterRequest = new ChapterRequest();
            chapterRequest.setBookId(bookId);
            chapterRequest.setTitle("测试标题" + i);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            for (int j=0;j<10;++j) {
                stringBuilder.append(stringBuilder);
            }
            chapterRequest.setContent(stringBuilder.toString());
            chapterRequest.setStatus(PublishStatus.RELEASE);
            bookWriteFacade.appendChapter(chapterRequest);
        }
    }

    @Override
    public boolean publishNovel(NovelPutDTO novelPutDTO) {
        String uuid = novelPutDTO.getUuid();
        Boolean bool = stringRedisTemplate.opsForValue().setIfAbsent(PUBLISH_MESSAGE_UUID + uuid, "true", 24, TimeUnit.HOURS);
        if (Boolean.TRUE.equals(bool)) {
            log.info("publish novel: {}", novelPutDTO);
            novelFacade.putNovel(novelPutDTO);
            return true;
        } else {
            log.warn("repeat publish: {}", novelPutDTO);
            return false;
        }
    }

    @Override
    public void broadCaster(BroadCastDTO broadCastDTO) {
        Tenant tenant = tenantManager.getTenant();
        publish(tenant, broadCastDTO);
        //广播父节点
        for (Tenant value : tenant.getParents().values()) {
            publish(value, broadCastDTO);
        }
        //广播子节点
        for (Tenant value : tenant.getChildren().values()) {
            publish(value, broadCastDTO);
        }
    }

    private void publish(Tenant tenant, BroadCastDTO broadCastDTO) {
        if (tenant == null) {
            return;
        }
        if (StrUtil.equals(tenant.getUid(), broadCastDTO.getSenderUid())) {
            return;
        }
        try {
            restTemplate.postForObject(tenant.getPublishAddress(), broadCastDTO, Response.class);
        } catch (Throwable e) {
            log.error("publish error: " + e.getMessage(), e);
        }
    }

}
