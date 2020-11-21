package cn.graydove.server.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChapterVO implements Serializable {

    private static final long serialVersionUID = 967613191811396722L;

    private Long id;

    private String title;
}
