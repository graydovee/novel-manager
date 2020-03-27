package com.ndovel.novel.service;

import com.ndovel.novel.model.entity.Authority;
import com.ndovel.novel.model.entity.User;

import java.util.List;

public interface UserService {

    List<Authority> getAuthorities();

    List<User> getUsers();

    User findUserById(Integer id);

    User saveUser(User user);

    User delUser(User user);

    User refresh(Integer id);

    Authority addRole(Authority authority);

    User userAddRole(Integer userId, Integer role_id);
}
