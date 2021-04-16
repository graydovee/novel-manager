package cn.graydove.ndovel.server.center.service;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.model.request.*;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.center.model.entity.Book;

/**
 * @author graydove
 */
public interface BookService {

    Long createBook(BookRequest bookRequest);

    Paging<BookVO> pageBook(BookPageRequest bookPageRequest);

    Boolean updateBook(UpdateBookRequest updateBookRequest);

    BookVO findBook(BookIdRequest bookIdRequest);
}
