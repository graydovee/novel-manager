package com.ndovel.ebook.service;

import com.ndovel.ebook.model.entity.Chapter;
import com.ndovel.ebook.model.entity.Content;

import java.util.List;
import java.util.Optional;

public interface ChapterService {

    List<Chapter> findAllChapterByBookId(Integer bookId);

    void delChapterByBookId(Integer bookId);

    Optional<Content> findContentById(Integer contentId);
}
