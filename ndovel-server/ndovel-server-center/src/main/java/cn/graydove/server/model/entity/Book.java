package cn.graydove.server.model.entity;

import cn.graydove.server.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

/**
 * @author graydove
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {

    private String name;

    private String introduce;

    @ManyToOne(targetEntity = Author.class)
    private Author author;

    @ManyToMany
    @JoinTable(name = "book_type",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "type_id", referencedColumnName = "id")})
    private Set<Category> category;

    /**
     * 封面
     */
    private String cover;

    private Long firstChapterId;

    private Long lastChapterId;

}
