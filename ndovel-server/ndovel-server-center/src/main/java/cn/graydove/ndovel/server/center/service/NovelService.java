package cn.graydove.ndovel.server.center.service;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.logger.model.dto.VisitStatisticDTO;
import cn.graydove.ndovel.server.api.model.request.NovelPutRequest;
import cn.graydove.ndovel.server.api.model.request.NovelSearchRequest;
import cn.graydove.ndovel.server.api.model.vo.NovelVO;

/**
 * @author graydove
 */
public interface NovelService {

    Long saveNovel(NovelPutRequest novelPutRequest);

    Paging<NovelVO> searchNovel(NovelSearchRequest novelSearchRequest);

    Boolean updateVisit(VisitStatisticDTO visitStatisticDTO);
}
