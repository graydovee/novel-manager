package cn.graydove.server.api;

import cn.graydove.server.model.vo.ChapterVO;

/**
 * @author graydove
 */
public interface BookReadFacade {

    ChapterVO findChapter(Long chapterId);
}
