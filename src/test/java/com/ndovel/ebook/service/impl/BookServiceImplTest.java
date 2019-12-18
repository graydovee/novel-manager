package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.ApplicationTests;
import com.ndovel.ebook.model.dto.BookDTO;
import org.junit.Test;
import org.springframework.data.domain.Page;

import static org.junit.Assert.*;

public class BookServiceImplTest extends ApplicationTests {



    @Test
    public void findByName() {
        Page<BookDTO> q = bookService.findByName("全", 0, 10);
        assertNotEquals(q.getTotalElements(),0);
    }
}