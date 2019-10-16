package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.exception.InvalidArgsException;
import com.ndovel.ebook.model.dto.ChapterDTO;
import com.ndovel.ebook.model.dto.ContentDTO;
import com.ndovel.ebook.model.dto.SpiderInfoDTO;
import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.model.entity.Chapter;
import com.ndovel.ebook.model.entity.Content;
import com.ndovel.ebook.model.entity.SpiderInfo;
import com.ndovel.ebook.repository.BookRepository;
import com.ndovel.ebook.repository.ChapterRepository;
import com.ndovel.ebook.repository.ContentRepository;
import com.ndovel.ebook.repository.SpiderInfoRepository;
import com.ndovel.ebook.service.AsyncService;
import com.ndovel.ebook.spider.core.NovelSpider;
import com.ndovel.ebook.spider.core.impl.CommonNovelSpider;
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

                spiderInfo.setFinalChapter(newChapter);
            }
        }
        spiderInfoRepository.save(spiderInfo);
    }

    @Async
    @Override
    public void down(SpiderInfo spiderInfo) {
        down(spiderInfo ,false);
    }
}
