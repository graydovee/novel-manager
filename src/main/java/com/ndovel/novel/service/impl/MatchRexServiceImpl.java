package com.ndovel.novel.service.impl;

import com.ndovel.novel.model.dto.MatchRexDTO;
import com.ndovel.novel.model.entity.MatchRex;
import com.ndovel.novel.repository.MatchRexRepository;
import com.ndovel.novel.service.MatchRexService;
import com.ndovel.novel.utils.DTOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchRexServiceImpl implements MatchRexService {

    private MatchRexRepository matchRexRepository;

    public MatchRexServiceImpl(MatchRexRepository matchRexRepository) {
        this.matchRexRepository = matchRexRepository;
    }

    @Override
    public List<MatchRexDTO> getAllRex() {
        List<MatchRex> l = matchRexRepository.findAllIsExist();
        return DTOUtils.listToDTOs(l, MatchRexDTO.class);
    }

    @Override
    public Page<MatchRexDTO> find(Integer index, Integer size) {
        Pageable pageable = PageRequest.of(index, size);
        return matchRexRepository.findIsExist(pageable)
                .map(matchRex -> new MatchRexDTO().init(matchRex));
    }

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

    @Override
    public void delById(Integer id) {
        matchRexRepository.deleteById(id);
    }
}
