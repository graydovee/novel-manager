package com.ndovel.ebook.service;

import com.ndovel.ebook.model.dto.MatchRexDTO;
import com.ndovel.ebook.model.entity.MatchRex;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MatchRexService {

    List<MatchRexDTO> getAllRex();

    Page<MatchRexDTO> find(Integer index, Integer size);

    MatchRexDTO save(MatchRex matchRex);

    void delById(Integer id);
}
