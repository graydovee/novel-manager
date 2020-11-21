package cn.graydove.ndovel.spider.service.impl;

import cn.graydove.common.exception.TaskException;
import cn.graydove.ndovel.spider.model.dto.NovelDTO;
import cn.graydove.ndovel.spider.service.SpiderService;
import cn.graydove.server.api.NovelService;
import cn.graydove.server.model.vo.NovelVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpiderServiceImpl implements SpiderService {

    @DubboReference
    private NovelService novelService;

    @Override
    public void spider(NovelDTO novelDTO) {

    }

    @Override
    public List<NovelVO> searchNovel(String key) {
        throw new TaskException("unsupported");
    }
}
