package com.ndovel.ebook.repository;

import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends BaseRepository<Book>, JpaSpecificationExecutor<Integer> {

    @Query("from Book book where book.name=?1 and book.author.name=?2 and book.deleted=0")
    Optional<Book> selBookByNameAndAuthor(String name, String author);
}
