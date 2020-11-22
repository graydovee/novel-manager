package cn.graydove.server.model.document;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

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
