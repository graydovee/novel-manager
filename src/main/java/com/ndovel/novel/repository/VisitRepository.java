package com.ndovel.novel.repository;

import com.ndovel.novel.model.dto.VisitDTO;
import com.ndovel.novel.model.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Integer>, CrudRepository<Visit, Integer> {

    @Query("select sum(visit) from Visit")
    Long sum();

    @Query("SELECT new com.ndovel.novel.model.dto.VisitDTO(SUM(v.visit),v.date) " +
            "FROM Visit v WHERE v.bookId=?1 GROUP BY v.date")
    List<VisitDTO> selAllByBookId(Integer bookId);

    @Query("SELECT new com.ndovel.novel.model.dto.VisitDTO(SUM(v.visit),v.date) " +
            "FROM Visit v GROUP BY v.date")
    List<VisitDTO> selAll();

    @Query("SELECT new com.ndovel.novel.model.dto.VisitDTO(v.bookId,SUM(v.visit)) " +
            "FROM Visit v GROUP BY v.bookId order by SUM(v.visit) desc ")
    List<VisitDTO> selBooksBVisit();


    @Query("SELECT new com.ndovel.novel.model.dto.VisitDTO(SUM(v.visit),v.date) " +
            "FROM Visit v WHERE v.bookId=?1 and v.date>=?2 and v.date<=?3 GROUP BY v.date")
    List<VisitDTO> selAllByBookIdAndTime(Integer bookId, Date begin, Date end);

    @Query("SELECT new com.ndovel.novel.model.dto.VisitDTO(SUM(v.visit),v.date) " +
            "FROM Visit v WHERE v.date>=?1 and v.date<=?2 GROUP BY v.date")
    List<VisitDTO> selAllByTime(Date begin, Date end);
}
