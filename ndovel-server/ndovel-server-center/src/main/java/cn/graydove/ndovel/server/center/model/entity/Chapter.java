package cn.graydove.ndovel.server.center.model.entity;

import cn.graydove.ndovel.server.api.enums.PublishStatus;
import cn.graydove.ndovel.server.center.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author graydove
 */
@Data
@Entity
@Table(indexes = {
        @Index(name = "idx_book_id", columnList = "bookId"),
        @Index(name = "idx_status", columnList = "status")
})
@EqualsAndHashCode(callSuper = true)
public class Chapter extends BaseEntity {

    private Long bookId;

    private String title;

    private PublishStatus status;

    private Long nextChapterId;

    private Long preChapterId;
}
