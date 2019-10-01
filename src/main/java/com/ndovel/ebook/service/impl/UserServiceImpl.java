package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.entity.Authority;
import com.ndovel.ebook.model.entity.User;
import com.ndovel.ebook.repository.AuthorityRepository;
import com.ndovel.ebook.repository.UserRepository;
import com.ndovel.ebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthorityRepository authorityRepository;


    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Authority> getAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User delUser(User user) {
        return userRepository.findById(user.getId()).flatMap(ur->{
            userRepository.delete(ur);
            return Optional.of(ur);
        }).orElse(null);
    }

    @Transactional
    @Override
    public User refresh(Integer id){
        return userRepository.findById(id).flatMap(user -> userRepository.refresh(user)).orElse(null);
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
