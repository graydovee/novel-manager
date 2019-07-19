package cn.graydove.ebook.web.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Table(name = "book")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(255) not null")
    private String name;

    @Column(name = "author", columnDefinition = "varchar(255) default ''")
    private String author;

    @Column(name = "type", columnDefinition = "int default 0")
    private Integer type;

    @Column(name = "first_chapter", columnDefinition = "int default 0")
    private Integer firstChapter;
}
