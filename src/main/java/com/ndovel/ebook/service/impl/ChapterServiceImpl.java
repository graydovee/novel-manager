package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.dto.ChapterDTO;
import com.ndovel.ebook.model.dto.ContentDTO;
import com.ndovel.ebook.model.entity.Chapter;
import com.ndovel.ebook.model.entity.Content;
import com.ndovel.ebook.repository.ChapterRepository;
import com.ndovel.ebook.repository.ContentRepository;
import com.ndovel.ebook.service.ChapterService;
import com.ndovel.ebook.utils.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Cacheable(value = {"chapter"},key = "#bookId")
    @Override
    public List<ChapterDTO> findAllChapterByBookId(Integer bookId) {
        List<Chapter> list = chapterRepository.findAllByBookId(bookId);
        return DTOUtils.listToDTOs(list, ChapterDTO.class);
    }

    @CacheEvict(value = {"chapter"}, key = "#bookId")
    @Override
    public void delChapterByBookId(Integer bookId) {
        chapterRepository.deleteAllByBookId(bookId);
    }

    @Override
    public Optional<ContentDTO> findContentById(Integer contentId) {
        return contentRepository.findOneIsExist(contentId)
                .map(content -> new ContentDTO().init(content));
    }
}
