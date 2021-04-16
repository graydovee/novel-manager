package cn.graydove.ndovel.logger.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author graydove
 */
@Data
public class VisitDTO implements Serializable {

    private static final long serialVersionUID = 2157478237736601769L;

    private String ip;

    private Long userId;

    private Long bookId;

    private Long chapterId;

    private Date createTime;
}
