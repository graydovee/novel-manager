package com.ndovel.ebook.repository;

import com.ndovel.ebook.model.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Integer>, CrudRepository<Visit, Integer> {

    @Query("select sum(visit) from Visit")
    Long sum();

    @Query("from Visit where bookId=?1")
    Optional<Visit> findByBookId(Integer bookId);

    @Modifying
    @Query(value = "UPDATE visit v,content cn,chapter cp SET v.visit=v.visit+1 " +
            "WHERE cp.content_id=cn.id AND v.book_id=cp.book_id AND cn.id=?1",
            nativeQuery = true)
    void addVisit(Integer contentId);
}
