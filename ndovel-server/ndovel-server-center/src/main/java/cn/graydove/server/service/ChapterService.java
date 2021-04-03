package cn.graydove.server.service;

import cn.graydove.server.model.request.ChapterRequest;
import cn.graydove.server.model.vo.ChapterVO;

/**
 * @author graydove
 */
public interface ChapterService {

    /**
     * 向末尾追加章节
     * @param chapterRequest chapterRequest
     * @return 新章节id
     */
    Long appendChapter(ChapterRequest chapterRequest);

    ChapterVO find(Long chapterId);
}
