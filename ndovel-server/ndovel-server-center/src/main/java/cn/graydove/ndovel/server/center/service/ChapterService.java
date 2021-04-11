package cn.graydove.ndovel.server.center.service;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.server.api.model.request.ChapterRequest;
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

    ChapterVO findDetail(Long chapterId);

    Paging<ChapterVO> pageByBookId(ChapterPageDTO chapterPageDTO);
}
