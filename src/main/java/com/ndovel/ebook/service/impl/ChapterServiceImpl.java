package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.dto.ChapterDTO;
import com.ndovel.ebook.model.dto.ContentDTO;
import com.ndovel.ebook.model.entity.Chapter;
import com.ndovel.ebook.repository.ChapterRepository;
import com.ndovel.ebook.repository.ContentRepository;
import com.ndovel.ebook.repository.VisitRepository;
import com.ndovel.ebook.service.ChapterService;
import com.ndovel.ebook.utils.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChapterServiceImpl implements ChapterService {


    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private VisitRepository visitRepository;

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
                    visitRepository.addVisit(content.getId());
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
