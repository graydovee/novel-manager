package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.dto.ChapterDTO;
import com.ndovel.ebook.model.dto.ContentDTO;
import com.ndovel.ebook.model.dto.base.BaseDTO;
import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.model.entity.Chapter;
import com.ndovel.ebook.model.entity.Content;
import com.ndovel.ebook.repository.BookRepository;
import com.ndovel.ebook.repository.ChapterRepository;
import com.ndovel.ebook.repository.ContentRepository;
import com.ndovel.ebook.service.AsyncService;
import com.ndovel.ebook.spider.bean.TaskCollection;
import com.ndovel.ebook.spider.core.NovelSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private TaskCollection taskCollection;

    @Autowired
    private BookRepository bookRepository;

    @CacheEvict(cacheNames = {"chapter"}, key = "#book.id")
    @Async
    @Override
    public void down(Book book, NovelSpider spider, Boolean flag){
        Chapter oldChapter = Optional.ofNullable(spider.getChapter())
                .map(BaseDTO::writeToDomain)
                .orElse(null);
        if (flag){
            spider.run();
        }

        //爬取
        while (spider.hasNext()){
            spider.run();

            ChapterDTO chapter = spider.getChapter();
            ContentDTO content = spider.getContent();
            if(chapter!=null && content!=null){
                log.info(chapter.getTitle());

                Content ct = content.writeToDomain();
                contentRepository.save(ct);
                Chapter cp = chapter.writeToDomain();
                cp.setContentId(ct.getId());
                chapterRepository.save(cp);

                if(oldChapter == null){
                    book.setFirstChapter(cp.getId());
                    bookRepository.save(book);
                } else {
                    oldChapter.setNextChapterId(cp.getId());
                    chapterRepository.save(oldChapter);
                }

                oldChapter = cp;
            }
        }
        taskCollection.put(new BookDTO().init(book), spider);
    }

    @Async
    @Override
    public void down(Book book, NovelSpider spider) {
        down(book,spider,false);
    }
}
