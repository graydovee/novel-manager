package com.ndovel.ebook.service;

import com.ndovel.ebook.model.dto.ChapterDTO;
import com.ndovel.ebook.model.dto.ContentDTO;

import java.util.List;
import java.util.Optional;

public interface ChapterService {

    List<ChapterDTO> findAllChapterByBookId(Integer bookId);

    void delChapterByBookId(Integer bookId);

    Optional<ContentDTO> findContentById(Integer contentId);
}
