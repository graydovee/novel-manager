package cn.graydove.ebook.web.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Table(name = "chapter")
@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "book_id", columnDefinition = "int not null")
    private Integer bookId;

    @Column(name = "this_page", columnDefinition = "varchar(255) default ''")
    private String thisPage;

    @Column(name = "next_page", columnDefinition = "varchar(255) default ''")
    private String nextPage;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "title", columnDefinition = "varchar(255) default ''")
    private String title;
}
