package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.exception.DataIsNotExistException;
import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.dto.ChapterDTO;
import com.ndovel.ebook.model.dto.ContentDTO;
import com.ndovel.ebook.model.dto.MatchRexDTO;
import com.ndovel.ebook.model.entity.*;
import com.ndovel.ebook.repository.*;
import com.ndovel.ebook.service.AsyncSpiderService;
import com.ndovel.ebook.spider.core.impl.CommonSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncSpiderServiceImpl implements AsyncSpiderService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    MatchRexRepository matchRexRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    ChapterRepository chapterRepository;

    @Async
    @Override
    public void downBook(BookDTO bookDTO, String url, String encode, Integer matchRexDTOId) {
        MatchRex mr = matchRexRepository.findOneIsExist(matchRexDTOId)
                .orElseThrow(DataIsNotExistException::new);

        MatchRexDTO matchRex = new MatchRexDTO().init(mr);


        Book book = bookDTO.writeToDomain();
        Author author = authorRepository.findOneByName(book.getAuthor().getName());
        if(author == null) {
            author = book.getAuthor();
            authorRepository.save(author);
        }

        book.setAuthor(author);
        bookRepository.save(book);

        CommonSpider spider = new CommonSpider(book.getId(),url,matchRex);

        if ("GBK".equalsIgnoreCase(encode)) {
            spider.setEncode(CommonSpider.Encode.GBK);
        }


        Chapter oldChapter = null;

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

    }
}
