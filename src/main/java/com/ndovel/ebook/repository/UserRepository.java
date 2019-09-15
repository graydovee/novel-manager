package com.ndovel.ebook.repository;

import com.ndovel.ebook.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("from User where username=?1")
    Optional<User> findByName(String username);
}
