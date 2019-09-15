package com.ndovel.ebook.service;

import com.ndovel.ebook.model.entity.Authority;
import com.ndovel.ebook.model.entity.User;

public interface UserService {

    User addUser(User user);

    Authority addRole(Authority authority);

    User userAddRole(Integer userId, Integer role_id);
}
