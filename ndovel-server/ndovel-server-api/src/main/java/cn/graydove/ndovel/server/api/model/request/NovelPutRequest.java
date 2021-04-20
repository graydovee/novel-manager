package cn.graydove.ndovel.server.api.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author graydove
 */
@Data
public class NovelPutRequest implements Serializable {

    private static final long serialVersionUID = -3076015173956104003L;

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
