package cn.graydove.ndovel.logger.server.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author graydove
 */
@Data
@Entity(name = "visit")
@Table(indexes = {
        @Index(name = "idx_userId", columnList = "userId"),
        @Index(name = "idx_bookId", columnList = "bookId"),
        @Index(name = "idx_chapterId", columnList = "chapterId"),
        @Index(name = "idx_createTime", columnList = "createTime"),
})
public class VisitDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private Long userId;

    private Long bookId;

    private Long chapterId;

    private Date createTime;
}
