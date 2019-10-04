package com.ndovel.ebook.service;

import com.ndovel.ebook.model.dto.ChapterDTO;
import com.ndovel.ebook.model.dto.ContentDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ChapterService {

    List<ChapterDTO> findAllChapterByBookId(Integer bookId);

    void delChapterByBookId(Integer bookId);

    Optional<ContentDTO> findContentById(Integer contentId);

    Long count(Integer bookId);

    Page<ChapterDTO> find(Integer bookId,Integer index, Integer size);
}
