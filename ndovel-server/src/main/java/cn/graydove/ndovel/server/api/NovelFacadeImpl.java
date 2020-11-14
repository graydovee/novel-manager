package cn.graydove.ndovel.server.api;

import cn.graydove.ndovel.api.facade.NovelFacade;
import cn.graydove.ndovel.api.response.Response;
import cn.graydove.ndovel.api.vo.ChapterVO;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NovelFacadeImpl implements NovelFacade {

    @Override
    public Response<List<ChapterVO>> requestChapterList() {
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
