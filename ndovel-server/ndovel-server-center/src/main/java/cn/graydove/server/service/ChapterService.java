package cn.graydove.server.service;

import cn.graydove.server.model.request.ChapterRequest;
import cn.graydove.server.model.vo.ChapterVO;

public interface ChapterService {

    Long appendChapter(ChapterRequest chapterRequest);

    ChapterVO find(Long chapterId);
}
