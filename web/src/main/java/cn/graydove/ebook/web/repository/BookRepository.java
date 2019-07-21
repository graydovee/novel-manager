package cn.graydove.ebook.web.repository;

import cn.graydove.ebook.web.model.entity.Book;
import cn.graydove.ebook.web.model.vo.BookVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    @Query("from Book where name like %?1% or author like %?1%")
    List<Book> selBookByName(String name);

    @Query("from Book where name=?1 and author=?2")
    Book selBookByNameAndAuthor(String name, String author);

    @Query("select new cn.graydove.ebook.web.model.vo.BookVO(book.id,book.name,author.name,type.name,book.firstChapter) from Book book"
            +" join Author author on author.id=book.author"
            +" join Type type on type.id=book.type"
            +" where author.name=?2 and book.name=?1"
    )
    BookVO selBookVOByNameAndAuthor(String name, String author);
}
