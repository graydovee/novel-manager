package com.ndovel.ebook.model.entity;

import com.ndovel.ebook.model.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "type")
@Entity
public class Type extends BaseEntity {

    @Column(name = "name")
    private String name;

}
