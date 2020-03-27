package com.ndovel.novel.repository;

import com.ndovel.novel.model.entity.Book;
import com.ndovel.novel.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends BaseRepository<Book>, JpaSpecificationExecutor<Integer> {

    @Query("from Book book where book.name=?1 and book.author.name=?2 and book.deleted=0 order by book.id desc")
    Optional<Book> selBookByNameAndAuthor(String name, String author);

    @Query("select book.id from Book book where book.deleted=0 order by book.id desc")
    List<Integer> findAllBookId();

    @Query(value = "select sum(visit) from content cn left join chapter cp on cn.id=cp.content_id where cp.book_id=?1", nativeQuery = true)
    Long countVisitByBookId(Integer bookId);

    @Modifying
    @Query(value = "update content cn left join chapter cp on cn.id=cp.content_id set cn.visit=0 where cp.book_id=?1", nativeQuery = true)
    void clearVisitByBookId(Integer bookId);
}
