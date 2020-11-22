package cn.graydove.server.service;

import cn.graydove.server.model.request.BookRequest;

public interface BookService {

    Long createBook(BookRequest bookRequest);
}
