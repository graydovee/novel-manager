package com.ndovel.ebook.controller.admin;

import com.ndovel.ebook.model.entity.User;
import com.ndovel.ebook.repository.UserRepository;
import com.ndovel.ebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public User register(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        user = userService.addUser(user);
        user.setPassword(null);
        return user;
    }

}
