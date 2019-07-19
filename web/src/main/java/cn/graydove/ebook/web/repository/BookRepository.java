package cn.graydove.ebook.web.repository;

import cn.graydove.ebook.web.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    @Query("from Book where name like %?1%")
    List<Book> selBookByName(String name);

    @Query("from Book where name=?1 and author=?2")
    Book selBookByNameAndAuthor(String name, String author);
}
