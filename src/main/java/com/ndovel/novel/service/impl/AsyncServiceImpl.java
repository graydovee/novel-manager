package com.ndovel.novel.service.impl;

import com.ndovel.novel.exception.InvalidArgsException;
import com.ndovel.novel.model.dto.ChapterDTO;
import com.ndovel.novel.model.dto.ContentDTO;
import com.ndovel.novel.model.dto.SpiderInfoDTO;
import com.ndovel.novel.model.entity.Book;
import com.ndovel.novel.model.entity.Chapter;
import com.ndovel.novel.model.entity.Content;
import com.ndovel.novel.model.entity.SpiderInfo;
import com.ndovel.novel.repository.BookRepository;
import com.ndovel.novel.repository.ChapterRepository;
import com.ndovel.novel.repository.ContentRepository;
import com.ndovel.novel.repository.SpiderInfoRepository;
import com.ndovel.novel.service.AsyncService;
import com.ndovel.novel.spider.core.NovelSpider;
import com.ndovel.novel.spider.core.impl.CommonNovelSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {

    private ContentRepository contentRepository;
    private ChapterRepository chapterRepository;
    private BookRepository bookRepository;
    private SpiderInfoRepository spiderInfoRepository;

    public AsyncServiceImpl(ContentRepository contentRepository,
                            ChapterRepository chapterRepository,
                            BookRepository bookRepository,
                            SpiderInfoRepository spiderInfoRepository) {
        this.contentRepository = contentRepository;
        this.chapterRepository = chapterRepository;
        this.bookRepository = bookRepository;
        this.spiderInfoRepository = spiderInfoRepository;
    }

    @Async
    @Override
    public void down(SpiderInfo spiderInfo, Boolean isNotFist){
        Book book = spiderInfo.getBook();
        if(book==null)
            throw new InvalidArgsException();

        NovelSpider spider = new CommonNovelSpider(new SpiderInfoDTO().init(spiderInfo));

        if (isNotFist)
            spider.run();


        int spiderTimes = 0;
        //爬取
        while (spider.hasNext()){
            String preUrl = spider.getUrl();
            spider.run();

            ChapterDTO chapter = spider.getChapter();
            ContentDTO content = spider.getContent();
            if(chapter!=null && content!=null){
                spiderInfo.setUrl(preUrl);

                log.info(chapter.getTitle());

                Content newContent = content.writeToDomain();
                newContent.setVisit(0L);
                contentRepository.save(newContent);
                Chapter newChapter = chapter.writeToDomain();
                newChapter.setContentId(newContent.getId());
                chapterRepository.save(newChapter);

                Chapter oldChapter = spiderInfo.getFinalChapter();
                if(spiderInfo.getFinalChapter() == null){
                    if(!isNotFist){
                        book.setFirstChapter(newChapter.getId());
                        bookRepository.save(book);
                    }
                } else {
                    oldChapter.setNextChapterId(newChapter.getId());
                    chapterRepository.save(oldChapter);
                }
                spiderTimes++;
                spiderInfo.setFinalChapter(newChapter);
            }
        }
        log.info("小说：《" + book.getName() + "》爬取结束，本次爬取 " + spiderTimes + " 章");
        spiderInfoRepository.save(spiderInfo);
    }

    @Async
    @Override
    public void down(SpiderInfo spiderInfo) {
        down(spiderInfo ,false);
    }
}
