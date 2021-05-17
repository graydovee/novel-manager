package cn.graydove.ndovel.server.center.service;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.logger.model.dto.VisitStatisticDTO;
import cn.graydove.ndovel.server.api.model.request.BookIdRequest;
import cn.graydove.ndovel.server.api.model.request.NovelPutRequest;
import cn.graydove.ndovel.server.api.model.request.NovelSearchRequest;
import cn.graydove.ndovel.server.api.model.vo.NovelVO;
import cn.graydove.ndovel.server.center.model.search.NovelDO;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author graydove
 */
public interface NovelService {

    Long saveNovel(NovelPutRequest novelPutRequest);

    NovelVO updateNovel(Long bookId, Function<NovelDO, NovelDO> novel);

    Boolean exist(Long bookId);

    Optional<NovelVO> findNovel(BookIdRequest bookIdRequest);

    Paging<NovelVO> searchNovel(NovelSearchRequest novelSearchRequest);

    Boolean updateVisit(VisitStatisticDTO visitStatisticDTO);
}
