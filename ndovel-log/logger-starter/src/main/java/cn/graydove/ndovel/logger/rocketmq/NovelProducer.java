package cn.graydove.ndovel.logger.rocketmq;

import cn.graydove.ndovel.client.UserContext;
import cn.graydove.ndovel.logger.model.dto.VisitDTO;
import cn.graydove.ndovel.logger.model.dto.VisitStatisticDTO;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author graydove
 */
@AllArgsConstructor
public class NovelProducer {

    private RocketMQTemplate rocketMqTemplate;

    public void logVisit(String ip, Long bookId, Long chapterId){
        VisitDTO visitDTO = new VisitDTO();
        visitDTO.setIp(ip);
        visitDTO.setUserId(UserContext.getUserId());
        visitDTO.setBookId(bookId);
        visitDTO.setChapterId(chapterId);
        visitDTO.setCreateTime(new Date());
        this.rocketMqTemplate.convertAndSend(NovelTopic.VISIT_TOPIC, visitDTO);
    }

    public void statisticVisit(VisitStatisticDTO visitStatisticDTO){
        this.rocketMqTemplate.convertAndSend(NovelTopic.VISIT_STATISTIC_TOPIC, visitStatisticDTO);
    }
}
