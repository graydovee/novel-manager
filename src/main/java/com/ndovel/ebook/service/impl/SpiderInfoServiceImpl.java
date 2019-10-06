package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.exception.InvalidArgsException;
import com.ndovel.ebook.model.entity.SpiderInfo;
import com.ndovel.ebook.repository.MatchRexRepository;
import com.ndovel.ebook.repository.SpiderInfoRepository;
import com.ndovel.ebook.service.SpiderInfoService;
import com.ndovel.ebook.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SpiderInfoServiceImpl implements SpiderInfoService {

    @Autowired
    private SpiderInfoRepository spiderInfoRepository;

    @Autowired
    private MatchRexRepository matchRexRepository;

    @Override
    public List<SpiderInfo> findAll() {
        return spiderInfoRepository.findAllIsExist();
    }

    @Override
    public List<SpiderInfo> findAllNotFinished() {
        return spiderInfoRepository.findAllNotFinish();
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
    public SpiderInfo save(Integer id, String url, Integer matchRexId){
        SpiderInfo s = spiderInfoRepository.findById(id)
                .orElseThrow(InvalidArgsException::new);

        if(!StringUtils.isEmpty(url)){
            s.setUrl(url);
        }

        if(matchRexId != null && matchRexId > 0){
            matchRexRepository.findOneIsExist(matchRexId).ifPresent(s::setMatchRex);
        }

        return spiderInfoRepository.save(s);
    }
}
