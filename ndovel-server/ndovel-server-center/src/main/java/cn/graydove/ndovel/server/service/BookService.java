package cn.graydove.ndovel.server.service;

import cn.graydove.common.response.Paging;
import cn.graydove.ndovel.server.model.dto.BookPageDTO;
import cn.graydove.ndovel.server.model.request.BookRequest;
import cn.graydove.ndovel.server.model.vo.BookVO;

/**
 * @author graydove
 */
public interface BookService {

    Long createBook(BookRequest bookRequest);

    Paging<BookVO> pageBook(BookPageDTO bookPageDTO);
}
