package cn.graydove.server.api;

import cn.graydove.common.response.Response;
import cn.graydove.server.model.ChapterVO;

import java.util.List;

public interface NovelService {
    Response<List<ChapterVO>> findChapterList();
}
