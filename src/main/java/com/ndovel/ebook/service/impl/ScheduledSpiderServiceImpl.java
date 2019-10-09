package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.entity.SpiderInfo;
import com.ndovel.ebook.service.AsyncService;
import com.ndovel.ebook.service.ScheduledSpiderService;
import com.ndovel.ebook.service.SpiderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ScheduledSpiderServiceImpl implements ScheduledSpiderService {

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private SpiderInfoService spiderInfoService;

    //second, minute, hour, day of month,mouth, day of week
    @Scheduled(cron = "0 0 1 * * ?")
    @Override
    public void autoDown() {
        List<SpiderInfo> spiderInfos = spiderInfoService.findAllNotFinished();

        for(SpiderInfo spiderInfo:spiderInfos){
            log.info("查询更新："+ spiderInfo.getBook().toString());
            asyncService.down(spiderInfo,true);
        }
    }


}
