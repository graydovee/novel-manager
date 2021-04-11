package cn.graydove.ndovel.user.server.repostitory;

import cn.graydove.ndovel.user.server.domain.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author graydove
 */
@Repository
public interface UserRepository extends JpaRepository<UserDO, Long> {

    Optional<UserDO> findByUsername(String username);

}
