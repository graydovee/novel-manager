package com.ndovel.ebook.service;

import com.ndovel.ebook.model.dto.BookDTO;

public interface AsyncSpiderService {

    void downBook(BookDTO bookDTO, String url, String encode, Integer matchRexDTOId);
}
