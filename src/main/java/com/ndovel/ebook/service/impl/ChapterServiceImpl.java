package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.entity.Chapter;
import com.ndovel.ebook.model.entity.Content;
import com.ndovel.ebook.repository.ChapterRepository;
import com.ndovel.ebook.repository.ContentRepository;
import com.ndovel.ebook.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public List<Chapter> findAllChapterByBookId(Integer bookId) {
        return chapterRepository.findAllByBookId(bookId);
    }

    @Override
    public void delChapterByBookId(Integer bookId) {
        List<Chapter> l = findAllChapterByBookId(bookId);
        l.forEach(chapter -> chapterRepository.delete(chapter));
    }

    @Override
    public Optional<Content> findContentById(Integer contentId) {
        return contentRepository.findOneIsExist(contentId);
    }
}
