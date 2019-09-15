package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.exception.InvalidArgsException;
import com.ndovel.ebook.model.entity.Authority;
import com.ndovel.ebook.model.entity.User;
import com.ndovel.ebook.repository.AuthorityRepository;
import com.ndovel.ebook.repository.UserRepository;
import com.ndovel.ebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthorityRepository authorityRepository;


    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Authority addRole(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public User userAddRole(Integer userId, Integer role_id) {
        User u = userRepository.findById(userId)
                .orElseThrow(NullPointerException::new);

        Authority a = authorityRepository.findById(role_id)
                .orElseThrow(NullPointerException::new);

        u.addAuthority(a);

        return userRepository.save(u);
    }
}
