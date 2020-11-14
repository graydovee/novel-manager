package cn.graydove.ndovel.api.facade;

import cn.graydove.ndovel.api.response.Response;
import cn.graydove.ndovel.api.vo.ChapterVO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface NovelFacade {

    @GetMapping("/chapter")
    Response<List<ChapterVO>> requestChapterList();
}
