package com.ndovel.novel.model.entity;

import com.ndovel.novel.model.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "book")
@Entity
public class Book extends BaseEntity {


    @Column(name = "name", columnDefinition = "varchar(255) not null")
    private String name;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "author", referencedColumnName = "id")
    private Author author;

    @ManyToMany(targetEntity = Type.class, fetch = FetchType.EAGER)
    @JoinTable(name = "book_type",
            joinColumns = {@JoinColumn(name = "book_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "type_id", referencedColumnName = "id")})
    private Set<Type> type = new HashSet<>();

    @Column(name = "first_chapter_id", columnDefinition = "int default 0")
    private Integer firstChapter;

    @Column(name = "introduce", columnDefinition = "text")
    private String introduce;
}
