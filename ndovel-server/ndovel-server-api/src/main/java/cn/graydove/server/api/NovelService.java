package cn.graydove.server.api;

import cn.graydove.server.model.request.NovelRequest;
import cn.graydove.server.model.vo.ChapterVO;
import cn.graydove.server.model.vo.NovelVO;

import java.util.List;

public interface NovelService {

    NovelVO createNovel(NovelRequest novelRequest);
}
