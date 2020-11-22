package cn.graydove.server.model.request;

import cn.graydove.common.model.BaseApi;
import cn.graydove.server.enums.BookStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

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