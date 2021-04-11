package cn.graydove.ndovel.user.server.repostitory;

import cn.graydove.ndovel.user.server.domain.entity.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * @author graydove
 */
public interface RoleRepository extends JpaRepository<RoleDO, Long> {

    Optional<RoleDO> findByName(String name);

    Set<RoleDO> findAllByNameIn(Collection<String> name);
}
