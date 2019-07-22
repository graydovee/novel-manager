package cn.graydove.ebook.web.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "author")
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;
}
