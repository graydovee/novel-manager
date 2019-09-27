package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.service.AsyncService;
import com.ndovel.ebook.service.ScheduledSpiderService;
import com.ndovel.ebook.spider.bean.TaskCollection;
import com.ndovel.ebook.spider.core.NovelSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ScheduledSpiderServiceImpl implements ScheduledSpiderService {

    @Autowired
    private TaskCollection taskCollection;

    @Autowired
    private AsyncService asyncService;

    //second, minute, hour, day of month,mouth, day of week
    @Scheduled(cron = "0 0 1 * * ?")
    @Override
    public void autoDown() {
        Map<BookDTO, NovelSpider> map = taskCollection.popAll();

        for(BookDTO bookDTO:map.keySet()){
            log.info("查询更新："+bookDTO.toString());
            NovelSpider spider = map.get(bookDTO);

            spider.update();
            asyncService.down(bookDTO.writeToDomain(), spider, true);
        }
    }


}
