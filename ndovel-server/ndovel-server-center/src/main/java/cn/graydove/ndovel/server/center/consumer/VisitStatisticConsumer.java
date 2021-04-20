package cn.graydove.ndovel.server.center.consumer;

import cn.graydove.ndovel.logger.model.dto.VisitStatisticDTO;
import cn.graydove.ndovel.logger.rocketmq.NovelTopic;
import cn.graydove.ndovel.server.center.service.NovelService;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author graydove
 */
@Component
@AllArgsConstructor
@RocketMQMessageListener(consumerGroup = "serverGroup", topic = NovelTopic.VISIT_STATISTIC_TOPIC, consumeMode = ConsumeMode.CONCURRENTLY)
public class VisitStatisticConsumer implements RocketMQListener<VisitStatisticDTO> {

    private NovelService novelService;

    @Override
    public void onMessage(VisitStatisticDTO message) {
        novelService.updateVisit(message);
    }
}
