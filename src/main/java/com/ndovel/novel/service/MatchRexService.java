package com.ndovel.novel.service;

import com.ndovel.novel.model.dto.MatchRexDTO;
import com.ndovel.novel.model.entity.MatchRex;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MatchRexService {

    List<MatchRexDTO> getAllRex();

    Page<MatchRexDTO> find(Integer index, Integer size);

    MatchRexDTO save(MatchRex matchRex);

    void delById(Integer id);
}
