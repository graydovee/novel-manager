package com.ndovel.ebook.service;

import com.ndovel.ebook.model.entity.MatchRex;

import java.util.List;

public interface MatchRexService {

    List<MatchRex> getAllRex();

    MatchRex save(MatchRex matchRex);

    void delById(Integer id);
}
