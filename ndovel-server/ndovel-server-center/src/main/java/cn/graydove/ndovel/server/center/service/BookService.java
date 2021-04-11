package cn.graydove.ndovel.server.center.service;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.model.dto.BookPageDTO;
import cn.graydove.ndovel.server.api.model.request.BookRequest;
import cn.graydove.ndovel.server.api.model.vo.BookVO;

/**
 * @author graydove
 */
public interface BookService {

    Long createBook(BookRequest bookRequest);

    Paging<BookVO> pageBook(BookPageDTO bookPageDTO);
}
