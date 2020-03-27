package com.ndovel.novel.repository;

import com.ndovel.novel.model.entity.User;
import com.ndovel.novel.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    @Query("from User where username=?1")
    Optional<User> findByName(String username);

}
