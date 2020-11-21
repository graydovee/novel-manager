package cn.graydove.server.api;

import cn.graydove.server.model.request.NovelRequest;
import cn.graydove.server.model.vo.AuthorVO;
import cn.graydove.server.model.vo.NovelVO;
import org.apache.dubbo.config.annotation.DubboService;


@DubboService
public class NovelServiceImpl implements NovelService {

    @Override
    public NovelVO createNovel(NovelRequest novelRequest) {
        NovelVO novelVO = new NovelVO();
        AuthorVO authorVO = new AuthorVO();
        authorVO.setName(novelRequest.getName());
        novelVO.setAuthor(authorVO);
        novelVO.setName(novelRequest.getName());
        novelVO.setIntroduce(novelRequest.getIntroduce());
        return novelVO;
    }

//    @Override
//    public Response<List<ChapterVO>> findChapterList() {
//        List<ChapterVO> list = new ArrayList<>();
//        ChapterVO chapterVO = new ChapterVO();
//        chapterVO.setTitle("title1");
//        chapterVO.setId(1L);
//        list.add(chapterVO);
//        chapterVO = new ChapterVO();
//        chapterVO.setTitle("title2");
//        chapterVO.setId(2L);
//        list.add(chapterVO);
//        return Response.success(list);
//    }
}
