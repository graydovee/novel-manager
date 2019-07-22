package cn.graydove.ebook.web;

import cn.graydove.ebook.web.model.entity.Author;
import cn.graydove.ebook.web.model.entity.Book;
import cn.graydove.ebook.web.model.entity.Chapter;
import cn.graydove.ebook.web.model.entity.Type;
import cn.graydove.ebook.web.repository.AuthorRepository;
import cn.graydove.ebook.web.repository.BookRepository;
import cn.graydove.ebook.web.repository.ChapterRepository;
import cn.graydove.ebook.web.repository.TypeRepository;
import cn.graydove.ebook.web.service.BookService;
import cn.graydove.ebook.web.service.ChapterService;
import cn.graydove.ebook.web.utils.StringUtils;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void viewTest(){
        Book book = new Book();
        Author author = new Author();
        book.setName("天牧");
        author.setName("厌笔萧生");
        authorRepository.save(author);
        book.setAuthor(author);
        Type type1 = new Type();
        type1.setName("玄幻");
        Type type2 = new Type();
        type2.setName("其他");

        typeRepository.save(type1);
        typeRepository.save(type2);

        book.getType().add(type1);
        book.getType().add(type2);

        bookRepository.save(book);
        System.out.println(book);

        Book book2 = bookRepository.selBookByNameAndAuthor("天牧","厌笔萧生");
        System.out.println(book2);
        List<Book> books = bookRepository.findAll();
        System.out.println(books);
    }

    @Test
    public void JpaTest(){
//        Book book = new Book();
//        book.setName("天牧");
//        book.setFirstChapter(2);
//        bookRepository.save(book);
//        System.out.println(book);
//        System.out.println(bookService.selBookByName("天牧"));
        Book book = bookRepository.selBookByNameAndAuthor("天牧","厌笔萧生");
        System.out.println(book);
        bookRepository.delete(book);
//        Book book = bookRepository.selBookByName("天").get(0);
//        List<Book> books = bookRepository.findAll();
//        System.out.println(books);
//        Chapter chapter1 = chapterRepository.findById(2).get();
//        System.out.println(chapter1);
//        Chapter chapter2 =  chapterRepository.selChapterByThisPage(chapter1.getNextPage(),book.getId());
//        System.out.println(chapter2);
//        book.setType(1143);
//        bookRepository.save(book);
//        Type type = new Type();
//        type.setName("玄幻");
//        typeRepository.save(type);
//        System.out.println();
    }

    @Test
    public void serviceTest(){
//        Book book = bookRepository.selBookByNameAndAuthor("天牧","厌笔萧生");
//        Book book = bookRepository.selBookByNameAndAuthor("遮天","辰东");
//        Chapter c = chapterService.getFirstChapter(book);
//        System.out.println(StringUtils.formatContent(c.getContent()));
//        while(c!=null && c.getNextPage()!=null){
//            c.setContent(StringUtils.formatContent(c.getContent()));
//            chapterRepository.save(c);
//            System.out.println(c.getTitle());
//            c = chapterService.getNextChapter(c);
//        }
//        System.out.println(StringUtils.formatContent(c.getContent()));
    }

//    @Test
//    public void unique(){
//        List<Chapter> list = chapterRepository.findAll();
//        for(Chapter chapter:list){
//            if(chapter.getThisPage().equals(chapter.getNextPage())){
//                System.out.println(chapter.getTitle()+"--"+chapter.getThisPage());
//                chapterRepository.delete(chapter);
//            }
//
//        }
//    }
}
