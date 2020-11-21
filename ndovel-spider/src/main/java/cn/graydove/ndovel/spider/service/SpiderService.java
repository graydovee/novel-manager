package cn.graydove.ndovel.spider.service;

import cn.graydove.ndovel.spider.model.dto.NovelDTO;
import cn.graydove.server.model.vo.NovelVO;

import java.util.List;

public interface SpiderService {

    void spider(NovelDTO novelDTO);

    List<NovelVO> searchNovel(String key);
}
