package cn.graydove.ndovel.server.center.model.entity;

import cn.graydove.ndovel.server.center.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author graydove
 */
@Data
@Entity
@Table(indexes = {
        @Index(name = "idx_user_id", columnList = "userId")
})
@EqualsAndHashCode(callSuper = true)
public class Author extends BaseEntity {

    @Column(unique = true, length = 50)
    private String name;

    private Long userId;

    public Author() {
    }
}
