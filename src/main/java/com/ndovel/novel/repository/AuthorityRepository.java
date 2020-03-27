package com.ndovel.novel.repository;

import com.ndovel.novel.model.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
