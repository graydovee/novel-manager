package cn.graydove.server.service;

import cn.graydove.server.model.request.BookRequest;

/**
 * @author graydove
 */
public interface BookService {

    Long createBook(BookRequest bookRequest);
}
