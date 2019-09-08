package com.ndovel.ebook.model.entity;

import com.ndovel.ebook.model.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "chapter")
@Entity
public class Chapter extends BaseEntity {

    @Column(name = "book_id", columnDefinition = "int not null")
    private Integer bookId;

    @Column(name = "this_page", columnDefinition = "varchar(255) default ''")
    private String thisPage;

    @Column(name = "next_page", columnDefinition = "varchar(255) default ''")
    private String nextPage;

    @Column(name = "content_id", columnDefinition = "int not null")
    private Integer contentId;

    @Column(name = "title", columnDefinition = "varchar(255) default ''")
    private String title;
}
