package com.ndovel.ebook.repository;

import com.ndovel.ebook.model.entity.User;
import com.ndovel.ebook.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    @Query("from User where username=?1")
    Optional<User> findByName(String username);

}
