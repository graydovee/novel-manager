package cn.graydove.ndovel.server.center.model.entity;

import cn.graydove.ndovel.server.center.model.entity.base.BaseEntity;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "category_type",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private Set<Category> category;

    /**
     * 封面
     */
    private String cover;

    private Long firstChapterId;

    private Long lastChapterId;

}
