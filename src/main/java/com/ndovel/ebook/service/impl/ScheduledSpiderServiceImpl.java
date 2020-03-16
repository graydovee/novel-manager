package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.entity.SpiderInfo;
import com.ndovel.ebook.repository.SpiderInfoRepository;
import com.ndovel.ebook.service.AsyncService;
import com.ndovel.ebook.service.ScheduledSpiderService;
import com.ndovel.ebook.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class ScheduledSpiderServiceImpl implements ScheduledSpiderService {

    private AsyncService asyncService;
    private SpiderInfoRepository spiderInfoRepository;
    private VisitService visitService;

    public ScheduledSpiderServiceImpl(AsyncService asyncService, SpiderInfoRepository spiderInfoRepository, VisitService visitService) {
        this.asyncService = asyncService;
        this.spiderInfoRepository = spiderInfoRepository;
        this.visitService = visitService;
    }

    //second, minute, hour, day of month,mouth, day of week
    @Scheduled(cron = "0 0 1 * * ?")
    @Override
    public void autoDown() {
        List<SpiderInfo> spiderInfos = spiderInfoRepository.findAllNotFinish();

        for(SpiderInfo spiderInfo:spiderInfos){
            log.info("查询更新："+ spiderInfo.getBook().toString());
            asyncService.down(spiderInfo,true);
        }
    }

    @Transactional
    @Scheduled(cron = "0 0 2 * * ?")
    @Override
    public void updateVisit() {
        log.info("更新阅读量!");
        visitService.updateVisit();
    }
}
