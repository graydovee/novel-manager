package cn.graydove.ndovel.server.center.service;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.model.request.*;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;

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

    ChapterVO findDetail(ChapterIdRequest chapterIdRequest);

    Paging<ChapterVO> pageByBookId(ChapterPageRequest chapterPageRequest);

    Paging<ChapterVO> pageAll(ChapterPageAllRequest chapterPageAllRequest);

    Boolean updateChapter(UpdateChapterRequest updateChapterRequest);
}
