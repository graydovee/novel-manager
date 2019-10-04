package com.ndovel.ebook.controller;

import com.ndovel.ebook.model.entity.Authority;
import com.ndovel.ebook.model.entity.User;
import com.ndovel.ebook.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class InitController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String init(){
        if(isFirst())
            return "init";
        else
            return "redirect:index.html";
    }

    @PostMapping("/init")
    public String init(String username, String password,HttpServletRequest request, HttpServletResponse response){

        if(!isFirst()){
            try {
                response.sendError(HttpStatus.NOT_FOUND.value());
            } catch (IOException e) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
                log.error(e.getMessage());
            }
            return "success";
        }


        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userService.saveUser(user);

        Authority role_user = new Authority();
        role_user.setName("ROLE_USER");
        Authority role_root = new Authority();
        role_root.setName("ROLE_ROOT");

        userService.addRole(role_user);
        userService.addRole(role_root);

        user.addAuthority(role_user);
        user.addAuthority(role_root);
        userService.saveUser(user);

        request.setAttribute("username",username);
        request.setAttribute("password",password);
        return "success";
    }

    private synchronized boolean isFirst(){
        List<User> users = userService.getUsers();
        List<Authority> authorities = userService.getAuthorities();

        return users.isEmpty() && authorities.isEmpty();
    }

}
