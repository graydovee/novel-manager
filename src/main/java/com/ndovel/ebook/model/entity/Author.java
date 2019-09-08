package com.ndovel.ebook.model.entity;

import com.ndovel.ebook.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Table(name = "author")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Author extends BaseEntity {

    @Column(name = "name")
    String name;
}
