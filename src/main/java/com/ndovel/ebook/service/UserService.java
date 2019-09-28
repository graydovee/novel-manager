package com.ndovel.ebook.service;

import com.ndovel.ebook.model.entity.Authority;
import com.ndovel.ebook.model.entity.User;

import java.util.List;

public interface UserService {

    List<Authority> getAuthorities();

    List<User> getUsers();

    User findUserById(Integer id);

    User saveUser(User user);

    User delUser(User user);

    void refresh(Integer id);

    Authority addRole(Authority authority);

    User userAddRole(Integer userId, Integer role_id);
}
