package cn.graydove.ebook.web.model.dto;

import cn.graydove.ebook.web.model.dto.base.BaseDTO;
import cn.graydove.ebook.web.model.entity.Chapter;
import lombok.Data;

@Data
public class ChapterDTO implements BaseDTO<ChapterDTO, Chapter> {
    private Integer id;

    private Integer bookId;

    private String thisPage;

    private String nextPage;

    private String content;

    private String title;
}
