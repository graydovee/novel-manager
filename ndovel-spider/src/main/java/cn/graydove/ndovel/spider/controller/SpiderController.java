package cn.graydove.ndovel.spider.controller;

import cn.graydove.common.response.Response;
import cn.graydove.ndovel.spider.model.dto.NovelDTO;
import cn.graydove.ndovel.spider.service.SpiderService;
import cn.graydove.server.enums.NovelStatusEnum;
import cn.graydove.server.model.request.NovelRequest;
import cn.graydove.server.model.vo.NovelVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@AllArgsConstructor
public class SpiderController {

    private SpiderService spiderService;

    @PostMapping("/spiderNovel")
    public void spiderNovel(@RequestBody @Valid NovelDTO novelDTO) {
        NovelRequest novelRequest = new NovelRequest();
        BeanUtils.copyProperties(novelDTO, novelRequest);
        novelRequest.setStatus(NovelStatusEnum.RELEASE);
    }

    @PostMapping("/searchNovel")
    public List<NovelVO> searchNovel(@Valid @NotBlank String key) {
        return spiderService.searchNovel(key);
    }
}
