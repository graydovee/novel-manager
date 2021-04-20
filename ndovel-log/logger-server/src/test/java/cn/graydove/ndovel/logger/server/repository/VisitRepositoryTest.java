package cn.graydove.ndovel.logger.server.repository;

import cn.graydove.ndovel.logger.model.dto.VisitStatisticDTO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VisitRepositoryTest {

    @Autowired
    private VisitRepository visitRepository;

    @Test
    void textVisit() {
        List<VisitStatisticDTO> collect = visitRepository.statistic().stream().map(map -> BeanUtil.toBean(map, VisitStatisticDTO.class)).collect(Collectors.toList());
        System.out.println(collect);
    }
}