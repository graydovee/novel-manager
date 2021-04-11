package cn.graydove.ndovel.server.model.request;

import cn.graydove.ndovel.server.enums.BookStatusEnum;
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

    private String cover;

    private Set<String> category;

    private BookStatusEnum status;
}