package cn.graydove.ndovel.server.center.model.search;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Set;

/**
 * @author graydove
 */
@Data
@Document(indexName = "novel")
public class NovelDO implements Serializable {

    private static final long serialVersionUID = 3046203740094214493L;

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private Long bookId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", scalingFactor = 0.8D)
    private String author;

    @Field(type = FieldType.Keyword)
    private String cover;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", scalingFactor = 0.2D)
    private String introduce;

    @Field(type = FieldType.Keyword, analyzer = "ik_max_word", scalingFactor = 0.2D)
    private Set<String> type;

    @Field(type = FieldType.Keyword)
    private String from;

    @Field(type = FieldType.Long)
    private Long visit;

    @Field(type = FieldType.Long)
    private Long createTime;

    @Field(type = FieldType.Long)
    private Long updateTime;
}
