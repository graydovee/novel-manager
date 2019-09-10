package com.ndovel.ebook.repository;

import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends BaseRepository<Book>, JpaSpecificationExecutor<Integer> {
    @Query("from Book where (name like %?1% or author.name like %?1%) and deleted=0")
    List<Book> selBookByName(String name);

    @Query("from Book book where book.name=?1 and book.author.name=?2 and book.deleted=0")
    Book selBookByNameAndAuthor(String name, String author);
}
