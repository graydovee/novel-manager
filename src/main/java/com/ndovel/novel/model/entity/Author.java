package com.ndovel.novel.model.entity;

import com.ndovel.novel.model.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "author")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseEntity {

    @Column(name = "name")
    private String name;
}
