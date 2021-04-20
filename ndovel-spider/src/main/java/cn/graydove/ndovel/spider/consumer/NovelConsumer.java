package cn.graydove.ndovel.spider.consumer;

import cn.graydove.ndovel.server.api.contant.ServerTopic;
import cn.graydove.ndovel.server.api.model.request.NovelPutRequest;
import cn.graydove.ndovel.spider.config.TenantManager;
import cn.graydove.ndovel.spider.model.CryptoData;
import cn.graydove.ndovel.spider.model.dto.BroadCastDTO;
import cn.graydove.ndovel.spider.model.dto.NovelPutDTO;
import cn.graydove.ndovel.spider.service.SpiderService;
import cn.graydove.ndovel.spider.util.CryptoUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author graydove
 */
@Slf4j
@Component
@AllArgsConstructor
@RocketMQMessageListener(consumerGroup = "spiderGroup", topic = ServerTopic.TOPIC_PUBLISH_NOVEL, consumeMode = ConsumeMode.CONCURRENTLY)
public class NovelConsumer implements RocketMQListener<NovelPutRequest> {

    private TenantManager tenantManager;

    private SpiderService spiderService;

    @Override
    public void onMessage(NovelPutRequest message) {
        log.info("start broadcast publish：{}", message);
        String uid = tenantManager.getTenant().getUid();
        NovelPutDTO novelPutDTO = BeanUtil.toBean(message, NovelPutDTO.class);
        novelPutDTO.setUuid(UUID.randomUUID().toString(true));
        BroadCastDTO encode = CryptoUtil.encode(novelPutDTO, uid, uid, tenantManager.getRsa());
        spiderService.broadCaster(encode);
    }
}
