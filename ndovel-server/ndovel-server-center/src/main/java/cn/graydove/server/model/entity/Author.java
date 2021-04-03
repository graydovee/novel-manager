package cn.graydove.server.model.entity;

import cn.graydove.server.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author graydove
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Author extends BaseEntity {

    @Column(unique = true, length = 50)
    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }
}
