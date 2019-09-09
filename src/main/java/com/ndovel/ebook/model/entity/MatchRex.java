package com.ndovel.ebook.model.entity;

import com.ndovel.ebook.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "match_rex")
public class MatchRex extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "info")
    private String info;

    @Column(name = "content_rex", nullable = false)
    private String ContentRex;

    @Column(name = "title_rex", nullable = false)
    private String titleRex;

    @Column(name = "next_page_rex", nullable = false)
    private String nextPageRex;
}
