package cn.graydove.ndovel.logger.server.service;

import cn.graydove.ndovel.logger.model.dto.VisitStatisticDTO;
import cn.graydove.ndovel.logger.rocketmq.NovelProducer;
import cn.graydove.ndovel.logger.server.repository.VisitRepository;
import cn.hutool.core.bean.BeanUtil;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author graydove
 */
@Component
@AllArgsConstructor
public class JobHandler {

    private final static Map<Long, Long> VISIT_STATISTIC = new HashMap<>();

    private VisitRepository visitRepository;

    private NovelProducer novelProducer;


    @XxlJob("statisticVisit")
    public void statisticVisit() throws Exception {
        XxlJobHelper.log("开始阅读量统计");
        List<VisitStatisticDTO> statistic = visitRepository.statistic().stream().map(map -> BeanUtil.toBean(map, VisitStatisticDTO.class)).collect(Collectors.toList());
        XxlJobHelper.log("共{}本小说需要更新阅读量", statistic.size());
        for (VisitStatisticDTO visitStatisticDTO : getChangedList(statistic)) {
            novelProducer.statisticVisit(visitStatisticDTO);
        }
    }

    private synchronized List<VisitStatisticDTO> getChangedList(List<VisitStatisticDTO> visitStatisticDTOList) {
        List<VisitStatisticDTO> newList = new ArrayList<>();
        for (VisitStatisticDTO visitStatisticDTO : visitStatisticDTOList) {
            Long bookId = visitStatisticDTO.getBookId();
            Long oldVisit = VISIT_STATISTIC.get(bookId);
            Long newVisit = visitStatisticDTO.getVisit();
            if (oldVisit == null || !oldVisit.equals(newVisit)) {
                VISIT_STATISTIC.put(visitStatisticDTO.getBookId(), newVisit);
                newList.add(visitStatisticDTO);
            }
        }
        return newList;
    }
}
