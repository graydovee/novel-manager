package com.ndovel.novel.controller.admin;

import com.ndovel.novel.exception.InvalidArgsException;
import com.ndovel.novel.model.entity.Authority;
import com.ndovel.novel.model.entity.User;
import com.ndovel.novel.model.vo.Response;
import com.ndovel.novel.service.UserService;
import com.ndovel.novel.utils.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/root")
public class AdminUserController {

    private PasswordEncoder passwordEncoder;
    private UserService userService;

    public AdminUserController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/register")
    public Response register(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        user = userService.saveUser(user);
        user.setPassword(null);
        return Response.success(user);
    }

    @GetMapping("/user")
    public Response getUser(){
        List<User> users = userService.getUsers();
        users.forEach(user -> user.setPassword(null));
        return Response.success(users);
    }

    @DeleteMapping("/user")
    public Response delUser(User user){
        User u = userService.delUser(user);
        if(u!=null){
            u.setPassword(null);
            return Response.success(u);
        }
        else
            return Response.error(null);
    }

    @PostMapping("/user")
    public Response refreshUser(Integer id){
        User u = userService.refresh(id);
        if(u!=null)
            return Response.success(u);
        else
            return Response.error("删除失败");
    }

    @PutMapping("/user")
    public Response updUser(User user){
        User u = userService.findUserById(user.getId());
        if(u==null)
            return Response.error("用户不存在");

        if(!StringUtils.isEmpty(user.getPassword())){
            u.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if(!StringUtils.isEmpty(user.getUsername())){
            u.setUsername(user.getUsername());
        }

        u = userService.saveUser(u);
        u.setPassword(null);
        return Response.success(u);
    }

    @GetMapping("/role")
    public Response getRole(){
        return Response.success(userService.getAuthorities());
    }

    @PostMapping("/role")
    public Response addRole(String name){
        Authority authority = new Authority();

        if(StringUtils.isEmpty(name))
            throw new InvalidArgsException();

        authority.setName(name);
        return Response.success(userService.addRole(authority));
    }

    @PutMapping("/role")
    public Response add_role(Integer userId,Integer roleId){
        User u = userService.userAddRole(userId, roleId);
        u.setPassword(null);
        return Response.success(u);
    }

}
