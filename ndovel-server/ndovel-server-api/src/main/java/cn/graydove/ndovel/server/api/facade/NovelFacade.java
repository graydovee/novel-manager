package cn.graydove.ndovel.server.api.facade;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.model.request.NovelPutRequest;
import cn.graydove.ndovel.server.api.model.request.NovelSearchRequest;
import cn.graydove.ndovel.server.api.model.vo.NovelVO;

/**
 * @author graydove
 */
public interface NovelFacade {

    void putNovel(NovelPutRequest novelPutRequest);

    Paging<NovelVO> searchNovel(NovelSearchRequest novelSearchRequest);
}
