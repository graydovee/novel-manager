package cn.graydove.ndovel.server.center.model.document;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author graydove
 */
@Data
@Document
public class ChapterDetail {

    @Id
    ObjectId id;

    @Indexed
    private Long chapterId;

    @Indexed
    private Long bookId;

    private String title;

    private String content;
}
