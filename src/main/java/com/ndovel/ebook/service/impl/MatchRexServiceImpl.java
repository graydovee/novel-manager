package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.dto.MatchRexDTO;
import com.ndovel.ebook.model.entity.MatchRex;
import com.ndovel.ebook.repository.MatchRexRepository;
import com.ndovel.ebook.service.MatchRexService;
import com.ndovel.ebook.utils.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchRexServiceImpl implements MatchRexService {

    @Autowired
    private MatchRexRepository matchRexRepository;

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
