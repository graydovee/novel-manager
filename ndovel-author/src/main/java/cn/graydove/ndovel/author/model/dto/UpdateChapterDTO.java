package cn.graydove.ndovel.author.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class UpdateChapterDTO implements Serializable {

    private static final long serialVersionUID = -6347803593395038483L;

    private Long id;

    private String content;

    private String title;
}
