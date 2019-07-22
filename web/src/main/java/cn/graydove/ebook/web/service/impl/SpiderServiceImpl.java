package cn.graydove.ebook.web.service.impl;

import cn.graydove.ebook.web.model.entity.Author;
import cn.graydove.ebook.web.repository.AuthorRepository;
import cn.graydove.ebook.web.repository.BookRepository;
import cn.graydove.ebook.web.repository.ChapterRepository;
import cn.graydove.ebook.web.service.SpiderService;
import cn.graydove.ebook.web.model.entity.Book;
import cn.graydove.ebook.web.model.entity.Chapter;
import cn.graydove.ebook.web.utils.StringUtils;
import cn.graydove.spider.NovelSpider;
import cn.graydove.spider.core.impl.BiqugeSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SpiderServiceImpl implements SpiderService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Async
    @Override
    public void downBook(@NonNull String bookName, @NonNull String authorName, @NonNull String bookNumber, @NonNull String firstPage, @NonNull String finalPage) {
        Book book = bookRepository.selBookByNameAndAuthor(bookName, authorName);

        if (book != null){
            return;
        }

        Author author = authorRepository.findByAuthorName(authorName);

        if(author==null){
            author = new Author();
            author.setName(authorName);
            authorRepository.save(author);
        }

        book = new Book();
        book.setName(bookName);
        book.setAuthor(author);
        bookRepository.save(book);

        NovelSpider spider = new BiqugeSpider(bookNumber, firstPage);

        Chapter chapter = new Chapter();
        chapter.setBookId(book.getId());
        chapter.setThisPage(firstPage);

        int c = 0;
        while (spider.hasNext()){
            spider.nextPage();
            c++;

            String content = spider.getContent();

            if(content!=null){
                content = StringUtils.formatContent(content);

                chapter.setContent(content);
                chapter.setTitle(spider.getTitle());
                chapter.setNextPage(spider.getNextPage());
                chapter.setId(null);

                if(!chapter.getThisPage().equals(chapter.getNextPage()) || chapter.getThisPage().equals(finalPage))
                    chapterRepository.save(chapter);

                log.info("已爬取：" + chapter.getTitle());
            }


            if(content==null || chapter.getThisPage().equals(finalPage)){
                if(content==null)
                    log.info("无内容，爬取结束！");
                break;
            }

            if(chapter.getThisPage().equals(firstPage)){
                book.setFirstChapter(chapter.getId());
                bookRepository.save(book);
            }
            chapter.setThisPage(chapter.getNextPage());

        }
        log.info(bookName+"爬取完毕！共" + c + "章");
    }
}
