package com.ndovel.ebook.model.entity;

import com.ndovel.ebook.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "spider_info")
public class SpiderInfo extends BaseEntity {

    @OneToOne(targetEntity = Book.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "book", referencedColumnName = "id")
    private Book book;

    @OneToOne(targetEntity = MatchRex.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "match_rex", referencedColumnName = "id")
    private MatchRex matchRex;

    @Column(name = "url", nullable = false)
    private String url;

    @OneToOne(targetEntity = Chapter.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "final_chapter", referencedColumnName = "id")
    private Chapter finalChapter;

    @Column(name = "finished", columnDefinition = "TINYINT default 0")
    private Boolean finished = false;
}
