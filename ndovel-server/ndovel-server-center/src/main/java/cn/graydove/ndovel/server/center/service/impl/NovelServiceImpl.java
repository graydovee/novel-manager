package cn.graydove.ndovel.server.center.service.impl;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.logger.model.dto.VisitStatisticDTO;
import cn.graydove.ndovel.server.api.model.request.NovelPutRequest;
import cn.graydove.ndovel.server.api.model.request.NovelSearchRequest;
import cn.graydove.ndovel.server.api.model.vo.NovelVO;
import cn.graydove.ndovel.server.center.converter.NovelConverter;
import cn.graydove.ndovel.server.center.model.search.NovelDO;
import cn.graydove.ndovel.server.center.repository.NovelRepository;
import cn.graydove.ndovel.server.center.service.NovelService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;

/**
 * @author graydove
 */
@Slf4j
@Service
@AllArgsConstructor
public class NovelServiceImpl implements NovelService {

    private NovelRepository novelRepository;

    @Override
    public Long saveNovel(NovelPutRequest novelPutRequest) {
        NovelDO novelDO = NovelConverter.toNovelDO(novelPutRequest);
        NovelDO save = novelRepository.save(novelDO);
        return save.getBookId();
    }

    @Override
    public Paging<NovelVO> searchNovel(NovelSearchRequest novelSearchRequest) {

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (StrUtil.isNotBlank(novelSearchRequest.getName())) {
            MatchQueryBuilder query = new MatchQueryBuilder("name", novelSearchRequest.getName());
            boolQueryBuilder.must(query);
        }
        if (StrUtil.isNotBlank(novelSearchRequest.getAuthor())) {
            MatchQueryBuilder query = new MatchQueryBuilder("author", novelSearchRequest.getAuthor());
            boolQueryBuilder.must(query);
        }
        if (StrUtil.isNotBlank(novelSearchRequest.getIntroduce())) {
            MatchQueryBuilder query = new MatchQueryBuilder("introduce", novelSearchRequest.getIntroduce());
            boolQueryBuilder.must(query);
        }
        if (CollectionUtil.isNotEmpty(novelSearchRequest.getType())) {
            StringJoiner stringJoiner = new StringJoiner(" ");
            for (String type : novelSearchRequest.getType()) {
                stringJoiner.add(type);
            }
            MatchQueryBuilder query = new MatchQueryBuilder("type", stringJoiner.toString());
            boolQueryBuilder.must(query);
        }
        Page<NovelDO> novelPage;
        novelPage = novelRepository.search(boolQueryBuilder, novelSearchRequest.toPageable());

        return Paging.ofWithMap(novelPage, NovelConverter::toNovelVO);
    }

    @Override
    public Boolean updateVisit(VisitStatisticDTO visitStatisticDTO) {
        if (visitStatisticDTO.getBookId() == null || visitStatisticDTO.getVisit() == null) {
            return false;
        }
        return novelRepository.findByBookId(visitStatisticDTO.getBookId()).map(novelDO -> {
            Long visit = visitStatisticDTO.getVisit();
            if (novelDO.getVisit() == null || novelDO.getVisit() < visit) {
                novelDO.setVisit(visit);
                novelRepository.save(novelDO);
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }).orElse(Boolean.FALSE);
    }
}
