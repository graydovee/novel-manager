package cn.graydove.ebook.web.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ChapterVO {
    Integer id;

    String title;

    Integer bookId;

    String thisPage;

    String nextPage;
}
