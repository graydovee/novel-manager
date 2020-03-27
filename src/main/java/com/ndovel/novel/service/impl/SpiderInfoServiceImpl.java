package com.ndovel.novel.service.impl;

import com.ndovel.novel.exception.InvalidArgsException;
import com.ndovel.novel.model.dto.SpiderInfoDTO;
import com.ndovel.novel.model.entity.SpiderInfo;
import com.ndovel.novel.repository.MatchRexRepository;
import com.ndovel.novel.repository.SpiderInfoRepository;
import com.ndovel.novel.service.SpiderInfoService;
import com.ndovel.novel.utils.DTOUtils;
import com.ndovel.novel.utils.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SpiderInfoServiceImpl implements SpiderInfoService {

    private SpiderInfoRepository spiderInfoRepository;
    private MatchRexRepository matchRexRepository;

    public SpiderInfoServiceImpl(SpiderInfoRepository spiderInfoRepository,
                                 MatchRexRepository matchRexRepository) {
        this.spiderInfoRepository = spiderInfoRepository;
        this.matchRexRepository = matchRexRepository;
    }

    @Override
    public List<SpiderInfoDTO> findAll() {
        return DTOUtils.listToDTOs(spiderInfoRepository.findAllIsExist(), SpiderInfoDTO.class);
    }

    @Override
    public Page<SpiderInfoDTO> find(Integer index, Integer size) {
        if(index!=null && size !=null){
            return spiderInfoRepository.findIsExist(PageRequest.of(index, size))
                    .map(spiderInfo -> new SpiderInfoDTO().init(spiderInfo));
        }
        return null;
    }

    @Override
    public Page<SpiderInfoDTO> find(Boolean finished, Integer index, Integer size) {
        if (finished == null) {
            return find(index, size);
        }

        return spiderInfoRepository.findIsExist((root, query, criteriaBuilder) -> {
                Path f = root.get("finished");
                return criteriaBuilder.equal(f, finished);
            }, PageRequest.of(index, size))
                .map(spiderInfo -> new SpiderInfoDTO().init(spiderInfo));
    }

    @Override
    public Page<SpiderInfoDTO> find(String name, Boolean finished, Integer index, Integer size) {
        if (StringUtils.isEmpty(name)) {
            return find(finished, index, size);
        }

        Specification<SpiderInfo> specification = (root, query, cb) -> {
            Predicate predicate = cb.like(root.get("book").get("name"), "%" + name + "%");
            if(finished != null) {
                predicate = cb.and(cb.equal(root.get("finished"), finished), predicate);
            }
            return predicate;
        };

        return spiderInfoRepository.findIsExist(specification, PageRequest.of(index, size))
                .map(spiderInfo -> new SpiderInfoDTO().init(spiderInfo));
    }


    @Override
    public Optional<SpiderInfoDTO> findIsExist(Integer id) {
        if (id == null)
            return Optional.empty();
        return spiderInfoRepository.findOneIsExist(id)
                .map(spiderInfo -> new SpiderInfoDTO().init(spiderInfo));
    }

    @Transactional
    @Override
    public Integer finishSpider(Integer id) {
        return spiderInfoRepository.setFinishedTrueById(id);
    }

    @Transactional
    @Override
    public Integer continueSpider(Integer id) {
        return spiderInfoRepository.setFinishedFalseById(id);
    }

    @Override
    public SpiderInfoDTO save(Integer id, String url, Integer matchRexId){
        SpiderInfo s = spiderInfoRepository.findById(id)
                .orElseThrow(InvalidArgsException::new);

        if(!StringUtils.isEmpty(url)){
            s.setUrl(url);
        }

        if(matchRexId != null && matchRexId > 0){
            matchRexRepository.findOneIsExist(matchRexId).ifPresent(s::setMatchRex);
        }

        SpiderInfo saved = spiderInfoRepository.save(s);
        return new SpiderInfoDTO().init(saved);
    }
}
