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
    @Query(value = "update visit a join (select book_id,sum(visit) as vt from content cn join chapter cp on cn.id = cp.content_id group by book_id) b on a.book_id=b.book_id set a.visit=b.vt",
            nativeQuery = true)
    void updateVisit();
}
