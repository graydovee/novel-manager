package cn.graydove.ndovel.server.api.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author graydove
 */
@Data
public class NovelVO implements Serializable {

    private static final long serialVersionUID = 463344717713282055L;

    private Long bookId;

    private String name;

    private String author;

    private String cover;

    private String introduce;

    private Set<String> type;

    private String from;

    private Long visit;

    private Date createTime;

    private Date updateTime;
}
