package cn.graydove.ebook.web.model.vo;

import cn.graydove.ebook.web.model.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookVO {

    private Integer id;

    private String name;

    private String author;

    private String type;

    private Integer firstChapter;
}
