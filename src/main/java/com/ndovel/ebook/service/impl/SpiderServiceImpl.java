package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.exception.DataIsNotExistException;
import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.dto.ContentDTO;
import com.ndovel.ebook.model.dto.MatchRexDTO;
import com.ndovel.ebook.model.dto.SpiderInfoDTO;
import com.ndovel.ebook.model.entity.*;
import com.ndovel.ebook.repository.*;
import com.ndovel.ebook.service.AsyncService;
import com.ndovel.ebook.service.SpiderService;
import com.ndovel.ebook.spider.core.impl.CommonSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class SpiderServiceImpl implements SpiderService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private MatchRexRepository matchRexRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AsyncService asyncService;

    @CacheEvict(cacheNames = {"book"}, allEntries = true)
    @Override
    public BookDTO spider(String bookName, String authorName, String url, Integer matchRexDTOId) {
        SpiderInfo spiderInfo = new SpiderInfo();
        spiderInfo.setUrl(url);

        spiderInfo.setMatchRex(matchRexRepository.findOneIsExist(matchRexDTOId)
                .orElseThrow(DataIsNotExistException::new));


        Book book = new Book();
        book.setName(bookName);

        Author author = authorRepository.findOneByName(authorName);
        if(author == null) {
            author = new Author();
            author.setName(authorName);
            authorRepository.save(author);
        }
        book.setAuthor(author);

        bookRepository.save(book);

        spiderInfo.setBook(book);

        asyncService.down(spiderInfo);

        return new BookDTO().init(book);
    }

    @Override
    public Map<String, String> spiderOne(String url, Integer matchRexId) {
        SpiderInfoDTO spiderInfo = new SpiderInfoDTO();
        spiderInfo.setUrl(url);
        spiderInfo.setMatchRex(new MatchRexDTO()
                .init(matchRexRepository.findOneIsExist(matchRexId)
                        .orElseThrow(DataIsNotExistException::new)));

        CommonSpider spider = new CommonSpider(spiderInfo);
        spider.run();

        Map<String, String> map = new HashMap<>();
        String contentStr = Optional.ofNullable(spider.getContent())
                .map(ContentDTO::getInfo)
                .orElse("");
        map.put("content", contentStr);
        map.put("next", spider.getUrl());

        return map;
    }


}
