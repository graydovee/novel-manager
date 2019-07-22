package cn.graydove.ebook.web.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Table(name = "book")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(255) not null")
    private String name;

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author", referencedColumnName = "id")
    private Author author;

    @ManyToMany(targetEntity = Type.class, fetch = FetchType.EAGER)
    @JoinTable(name = "book_type",
            joinColumns = {@JoinColumn(name = "book_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "type_id", referencedColumnName = "id")})
    private Set<Type> type = new HashSet<>();

    @Column(name = "first_chapter", columnDefinition = "int default 0")
    private Integer firstChapter;
}
