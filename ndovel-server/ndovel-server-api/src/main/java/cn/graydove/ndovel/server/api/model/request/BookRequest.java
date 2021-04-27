package cn.graydove.ndovel.server.api.model.request;

import cn.graydove.ndovel.server.api.enums.PublishStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author graydove
 */
@Data
public class BookRequest implements Serializable {

    private static final long serialVersionUID = 8212675880709769415L;

    private String name;

    private String introduce;

    private String author;

    private Long authorId;

    private String cover;

    private Set<String> category;

    private PublishStatus status;
}