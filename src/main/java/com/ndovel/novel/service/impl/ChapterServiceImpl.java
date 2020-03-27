package com.ndovel.novel.service.impl;

import com.ndovel.novel.model.dto.ChapterDTO;
import com.ndovel.novel.model.dto.ContentDTO;
import com.ndovel.novel.model.entity.Chapter;
import com.ndovel.novel.repository.ChapterRepository;
import com.ndovel.novel.repository.ContentRepository;
import com.ndovel.novel.service.ChapterService;
import com.ndovel.novel.utils.DTOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {


    private ChapterRepository chapterRepository;
    private ContentRepository contentRepository;

    public ChapterServiceImpl(ChapterRepository chapterRepository,
                              ContentRepository contentRepository) {
        this.chapterRepository = chapterRepository;
        this.contentRepository = contentRepository;
    }

    @Override
    public List<ChapterDTO> findAllChapterByBookId(Integer bookId) {
        List<Chapter> list = chapterRepository.findAllByBookId(bookId);
        return DTOUtils.listToDTOs(list, ChapterDTO.class);
    }

    @Override
    public Optional<ChapterDTO> findById(Integer chapterId) {
        return chapterRepository.findById(chapterId)
                .map(chapter -> new ChapterDTO().init(chapter));
    }

    @Transactional
    @Override
    public void delChapterByBookId(Integer bookId) {
        chapterRepository.deleteAllByBookId(bookId);
    }

    @Transactional
    @Override
    public Optional<ContentDTO> findContentById(Integer contentId) {
        return contentRepository.findOneIsExist(contentId)
                .map(content -> {
                    contentRepository.addVisit(content.getId());
                    return new ContentDTO().init(content);
                });
    }

    @Override
    public Long count(Integer bookId) {
        return chapterRepository.countIsExistById(bookId);
    }

    @Override
    public Page<ChapterDTO> find(Integer bookId, Integer index, Integer size){
        Pageable pageable = PageRequest.of(index, size);
        return chapterRepository.findIsExist(((root, query, criteriaBuilder) -> {
            Path<Object> id = root.get("bookId");
            return criteriaBuilder.equal(id, bookId);
        }),pageable).map(chapter -> new ChapterDTO().init(chapter));
    }
}
