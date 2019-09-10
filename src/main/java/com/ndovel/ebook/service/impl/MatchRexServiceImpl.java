package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.dto.MatchRexDTO;
import com.ndovel.ebook.model.entity.MatchRex;
import com.ndovel.ebook.repository.MatchRexRepository;
import com.ndovel.ebook.service.MatchRexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchRexServiceImpl implements MatchRexService {

    @Autowired
    private MatchRexRepository matchRexRepository;

    @Override
    public List<MatchRex> getAllRex() {
        return matchRexRepository.findAllIsExist();
    }

    @Override
    public MatchRex save(MatchRex matchRex) {
        if(!(matchRex==null ||
                matchRex.getTitleRex()==null ||
                matchRex.getContentRex()==null ||
                matchRex.getNextPageRex()==null ||
                matchRex.getName()==null)){
            matchRexRepository.save(matchRex);
        }
        return matchRex;
    }

    @Override
    public void delById(Integer id) {
        matchRexRepository.deleteById(id);
    }
}
