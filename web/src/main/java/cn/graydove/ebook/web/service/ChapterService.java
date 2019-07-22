package cn.graydove.ebook.web.service;

import cn.graydove.ebook.web.model.entity.Book;
import cn.graydove.ebook.web.model.entity.Chapter;
import cn.graydove.ebook.web.model.vo.ChapterVO;

import java.util.List;
import java.util.Map;

public interface ChapterService {

    Chapter getFirstChapter(Book book);

    Chapter getNextChapter(Chapter chapter);

    List<ChapterVO> findAllByBookId(Integer bookId);

}
