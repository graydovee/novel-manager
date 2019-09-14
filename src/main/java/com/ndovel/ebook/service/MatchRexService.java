package com.ndovel.ebook.service;

import com.ndovel.ebook.model.dto.MatchRexDTO;
import com.ndovel.ebook.model.entity.MatchRex;

import java.util.List;

public interface MatchRexService {

    List<MatchRexDTO> getAllRex();

    MatchRexDTO save(MatchRex matchRex);

    void delById(Integer id);
}
