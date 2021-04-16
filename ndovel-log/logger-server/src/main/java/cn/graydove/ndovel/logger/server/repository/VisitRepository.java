package cn.graydove.ndovel.logger.server.repository;

import cn.graydove.ndovel.logger.server.model.entity.VisitDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author graydove
 */
@Repository
public interface VisitRepository extends JpaRepository<VisitDO, Long> {
}
