package cn.graydove.ndovel.logger.server.consumer;

import cn.graydove.ndovel.logger.model.dto.VisitDTO;
import cn.graydove.ndovel.logger.rocketmq.NovelTopic;
import cn.graydove.ndovel.logger.server.model.entity.VisitDO;
import cn.graydove.ndovel.logger.server.repository.VisitRepository;
import cn.hutool.core.bean.BeanUtil;
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
@RocketMQMessageListener(consumerGroup = "LoggerGroup", topic = NovelTopic.VISIT_TOPIC, consumeMode = ConsumeMode.CONCURRENTLY)
public class VisitConsumer implements RocketMQListener<VisitDTO> {

    private VisitRepository visitRepository;

    @Override
    public void onMessage(VisitDTO visitDTO) {
        VisitDO visitDO = BeanUtil.toBean(visitDTO, VisitDO.class);
        visitRepository.save(visitDO);
    }
}
