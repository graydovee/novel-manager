package cn.graydove.server.api;

import cn.graydove.common.response.Response;
import cn.graydove.server.model.ChapterVO;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;

@DubboService
public class NovelServiceImpl implements NovelService {

    @Override
    public Response<List<ChapterVO>> findChapterList() {
        List<ChapterVO> list = new ArrayList<>();
        ChapterVO chapterVO = new ChapterVO();
        chapterVO.setTitle("title1");
        chapterVO.setId(1L);
        list.add(chapterVO);
        chapterVO = new ChapterVO();
        chapterVO.setTitle("title2");
        chapterVO.setId(2L);
        list.add(chapterVO);
        return Response.success(list);
    }
}
