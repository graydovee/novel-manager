package cn.graydove.ndovel.spider.controller;

import cn.graydove.common.response.Response;
import cn.graydove.server.api.NovelService;
import cn.graydove.server.model.ChapterVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @DubboReference
    private NovelService novelService;

    @Value("${novel.version}")
    private String version;

    @GetMapping("/chapter")
    public List<ChapterVO> requestChapterList() {
        return novelService.findChapterList().getResult();
    }

    @GetMapping("/test1")
    public Response<List<ChapterVO>> test1() {
        return novelService.findChapterList();
    }
    @GetMapping("/test2")
    public String test2() {
        return "test2 : " + version;
    }
}
