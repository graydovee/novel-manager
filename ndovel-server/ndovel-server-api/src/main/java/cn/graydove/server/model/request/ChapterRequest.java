package cn.graydove.server.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class ChapterRequest implements Serializable {

    private static final long serialVersionUID = -8162793803437973471L;

    private Long bookId;

    private String title;

    private String content;
}
