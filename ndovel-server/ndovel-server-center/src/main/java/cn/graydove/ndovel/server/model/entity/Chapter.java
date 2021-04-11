package cn.graydove.ndovel.server.model.entity;

import cn.graydove.ndovel.server.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * @author graydove
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Chapter extends BaseEntity {

    private Long bookId;

    private String title;

    private Long nextChapterId;

    private Long preChapterId;
}
