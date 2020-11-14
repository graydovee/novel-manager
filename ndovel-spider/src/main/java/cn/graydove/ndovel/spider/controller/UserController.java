package cn.graydove.ndovel.spider.controller;

import cn.graydove.ndovel.api.vo.ChapterVO;
import cn.graydove.ndovel.spider.api.openfeign.NovelFacadeFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private NovelFacadeFeign novelFacadeFeign;

    public UserController(NovelFacadeFeign novelFacadeFeign) {
        this.novelFacadeFeign = novelFacadeFeign;
    }

    @GetMapping("/chapter")
    public List<ChapterVO> requestChapterList() {
        return novelFacadeFeign.requestChapterList().getResult();
    }

}
