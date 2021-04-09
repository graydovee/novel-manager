package cn.graydove.server.service;

import cn.graydove.common.response.Paging;
import cn.graydove.server.model.dto.ChapterPageDTO;
import cn.graydove.server.model.request.ChapterRequest;
import cn.graydove.server.model.vo.ChapterVO;

import java.util.List;

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
