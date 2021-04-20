package cn.graydove.ndovel.spider.runner;

import cn.graydove.ndovel.common.response.Response;
import cn.graydove.ndovel.server.api.model.vo.NovelVO;
import cn.graydove.ndovel.spider.config.SpiderProperties;
import cn.graydove.ndovel.spider.config.TenantManager;
import cn.graydove.ndovel.spider.model.dto.NovelPutDTO;
import cn.graydove.ndovel.spider.model.dto.RegisterDTO;
import cn.graydove.ndovel.spider.model.vo.TenantVO;
import cn.graydove.ndovel.spider.service.SpiderService;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author graydove
 */
@Slf4j
@Component
@AllArgsConstructor
public class RegisterRunner implements ApplicationRunner {

    private SpiderProperties spiderProperties;

    private RestTemplate restTemplate;

    private TenantManager tenantManager;

    private SpiderService spiderService;

    @Override
    @SuppressWarnings("unchecked")
    public void run(ApplicationArguments args) throws Exception {
        tenantManager.refreshParent();
        //同步数据
        List<NovelVO> result = null;
        for (SpiderProperties.Invitor invitor : spiderProperties.getInvitor()) {
            try {
                Response response = restTemplate.getForObject(invitor.getSyncAddress(), Response.class);
                Optional<Object> o = Optional.ofNullable(response).map(Response::getResult);
                if (!o.isPresent()) {
                    continue;
                }
                result = (List<NovelVO>) o.get();
            } catch (Throwable ignored) {
            }

            if (result == null) {
                continue;
            }
            try {
                for (NovelVO novelVO : result) {
                    NovelPutDTO novelPutDTO = new NovelPutDTO();
                    novelPutDTO.setUuid(UUID.fastUUID().toString(true));
                    novelPutDTO.setBookId(novelVO.getBookId());
                    novelPutDTO.setName(novelVO.getName());
                    novelPutDTO.setAuthor(novelVO.getAuthor());
                    novelPutDTO.setCover(novelVO.getCover());
                    novelPutDTO.setIntroduce(novelVO.getIntroduce());
                    novelPutDTO.setType(novelVO.getType());
                    novelPutDTO.setFrom(novelVO.getFrom());
                    novelPutDTO.setVisit(novelVO.getVisit());
                    novelPutDTO.setCreateTime(novelVO.getCreateTime());
                    novelPutDTO.setUpdateTime(novelVO.getUpdateTime());
                    spiderService.publishNovel(novelPutDTO);
                }
            } catch (Throwable e) {
                log.error("sync data error" + e.getMessage(), e);
            }
            break;
        }
    }

}
