package cn.graydove.ndovel.logger.server.repository;

import cn.graydove.ndovel.logger.model.dto.VisitStatisticDTO;
import cn.graydove.ndovel.logger.server.model.entity.VisitDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author graydove
 */
@Repository
public interface VisitRepository extends JpaRepository<VisitDO, Long> {

    @Query(value = "SELECT v.book_id bookId, COUNT(v.book_id) visit FROM visit v GROUP BY v.book_id", nativeQuery = true)
    List<Map> statistic();
}
