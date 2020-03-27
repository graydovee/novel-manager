package com.ndovel.novel.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode
@Table(name = "visit")
@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "visit", columnDefinition = "bigint(20) default 0")
    private Long visit;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
}
