package cn.graydove.ndovel.server.center.repository;

import cn.graydove.ndovel.server.center.model.search.NovelDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author graydove
 */
@Repository
public interface NovelRepository extends ElasticsearchRepository<NovelDO, String> {

    Optional<NovelDO> findByBookId(Long bookId);
}
