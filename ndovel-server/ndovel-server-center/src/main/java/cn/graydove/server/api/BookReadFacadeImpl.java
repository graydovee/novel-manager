package cn.graydove.server.api;

import cn.graydove.server.model.request.ChapterRequest;
import cn.graydove.server.model.vo.ChapterVO;
import cn.graydove.server.service.ChapterService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author graydove
 */
@DubboService
@AllArgsConstructor
public class BookReadFacadeImpl implements BookReadFacade {

    private ChapterService chapterService;


    @Override
    public ChapterVO findChapter(Long chapterId) {
        return chapterService.find(chapterId);
    }
}
