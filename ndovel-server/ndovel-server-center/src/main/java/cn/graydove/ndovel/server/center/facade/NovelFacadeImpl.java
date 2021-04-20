package cn.graydove.ndovel.server.center.facade;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.facade.NovelFacade;
import cn.graydove.ndovel.server.api.model.request.NovelPutRequest;
import cn.graydove.ndovel.server.api.model.request.NovelSearchRequest;
import cn.graydove.ndovel.server.api.model.vo.NovelVO;
import cn.graydove.ndovel.server.center.service.NovelService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author graydove
 */
@DubboService
@AllArgsConstructor
public class NovelFacadeImpl implements NovelFacade {

    private NovelService novelService;

    @Override
    public void putNovel(NovelPutRequest novelPutRequest) {
        novelService.saveNovel(novelPutRequest);
    }

    @Override
    public Paging<NovelVO> searchNovel(NovelSearchRequest novelSearchRequest) {
        return novelService.searchNovel(novelSearchRequest);
    }
}
