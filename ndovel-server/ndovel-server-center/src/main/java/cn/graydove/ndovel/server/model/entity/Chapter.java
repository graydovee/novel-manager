package cn.graydove.ndovel.server.model.entity;

import cn.graydove.ndovel.server.model.entity.base.BaseEntity;
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
        @Index(name = "idx_book_id", columnList = "book_id")
})
@EqualsAndHashCode(callSuper = true)
public class Chapter extends BaseEntity {

    private Long bookId;

    private String title;

    private Long nextChapterId;

    private Long preChapterId;
}
