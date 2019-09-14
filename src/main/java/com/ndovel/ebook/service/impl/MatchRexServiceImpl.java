package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.dto.MatchRexDTO;
import com.ndovel.ebook.model.entity.MatchRex;
import com.ndovel.ebook.repository.MatchRexRepository;
import com.ndovel.ebook.service.MatchRexService;
import com.ndovel.ebook.utils.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchRexServiceImpl implements MatchRexService {

    @Autowired
    private MatchRexRepository matchRexRepository;

    @Cacheable(cacheNames = {"matchRex"})
    @Override
    public List<MatchRexDTO> getAllRex() {
        List<MatchRex> l = matchRexRepository.findAllIsExist();
        return DTOUtils.listToDTOs(l, MatchRexDTO.class);
    }

    @CacheEvict(cacheNames = {"matchRex"}, allEntries = true)
    @Override
    public MatchRexDTO save(MatchRex matchRex) {
        if(!(matchRex==null ||
                matchRex.getTitleRex()==null ||
                matchRex.getContentRex()==null ||
                matchRex.getNextPageRex()==null ||
                matchRex.getName()==null)){
            matchRexRepository.save(matchRex);
        }
        return new MatchRexDTO().init(matchRex);
    }

    @CacheEvict(cacheNames = {"matchRex"}, allEntries = true)
    @Override
    public void delById(Integer id) {
        matchRexRepository.deleteById(id);
    }
}
