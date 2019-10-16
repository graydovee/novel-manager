package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.exception.DataIsNotExistException;
import com.ndovel.ebook.model.dto.*;
import com.ndovel.ebook.model.entity.*;
import com.ndovel.ebook.repository.*;
import com.ndovel.ebook.service.AsyncService;
import com.ndovel.ebook.service.SpiderService;
import com.ndovel.ebook.spider.core.IndexSpider;
import com.ndovel.ebook.spider.core.NovelSpider;
import com.ndovel.ebook.spider.core.SearchSpider;
import com.ndovel.ebook.spider.core.impl.CommonNovelSpider;
import com.ndovel.ebook.spider.core.impl.IndexSpiderImpl;
import com.ndovel.ebook.spider.core.impl.SearchSpiderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SpiderServiceImpl implements SpiderService {

    private AuthorRepository authorRepository;
    private MatchRexRepository matchRexRepository;
    private BookRepository bookRepository;
    private VisitRepository visitRepository;
    private SpiderInfoRepository spiderInfoRepository;
    private AsyncService asyncService;

    public SpiderServiceImpl(AuthorRepository authorRepository,
                             MatchRexRepository matchRexRepository,
                             BookRepository bookRepository,
                             VisitRepository visitRepository,
                             SpiderInfoRepository spiderInfoRepository,
                             AsyncService asyncService) {
        this.authorRepository = authorRepository;
        this.matchRexRepository = matchRexRepository;
        this.bookRepository = bookRepository;
        this.visitRepository = visitRepository;
        this.spiderInfoRepository = spiderInfoRepository;
        this.asyncService = asyncService;
    }

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

        //访问量表
        Visit visit = new Visit();
        visit.setBookId(book.getId());
        visit.setVisit(0L);
        visitRepository.save(visit);

        spiderInfo.setBook(book);

        asyncService.down(spiderInfo);

        return new BookDTO().init(book);
    }

    @Override
    public TempChapter spiderOne(String url, Integer matchRexId) {
        NovelSpider spider = getSpider(url, matchRexId);
        spider.run();
        return spider.getTempChapter();
    }

    @Override
    public SpiderInfoDTO update(Integer spiderInfoId) {
        Object obj = spiderInfoRepository.findOneIsExist(spiderInfoId).map(spiderInfo -> {
            asyncService.down(spiderInfo, true);
            return new SpiderInfoDTO().init(spiderInfo);
        }).orElse(null);
        return (SpiderInfoDTO)obj;
    }

    @Override
    public List<SpiderIndex> spiderByName(String name) {
        SearchSpider searchSpider = new SearchSpiderImpl();
        return searchSpider.findAllIndex(name);
    }

    @Override
    public List<TempChapter> spiderByIndex(String url) {
        IndexSpider indexSpider = new IndexSpiderImpl();

        return indexSpider.getIndex(url);
    }

    private NovelSpider getSpider(String url, Integer matchRexId){
        SpiderInfoDTO spiderInfo = new SpiderInfoDTO();
        spiderInfo.setUrl(url);
        MatchRex rex = null;
        if (matchRexId > 0){
            rex = matchRexRepository.findOneIsExist(matchRexId).orElse(null);
        }
        if (rex == null) {
            Pageable pageable = PageRequest.of(0,1);
            Page<MatchRex> page = matchRexRepository.findIsExist(pageable);
            if (page.getTotalElements() > 0){
                rex =  page.getContent().get(0);
            }else {
                throw new DataIsNotExistException();
            }
            spiderInfo.setMatchRex(new MatchRexDTO().init(rex));
        }

        return new CommonNovelSpider(spiderInfo);
    }


}
