package com.ndovel.ebook.controller;

import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.entity.User;
import com.ndovel.ebook.model.vo.Response;
import com.ndovel.ebook.service.BookService;
import com.ndovel.ebook.service.SpiderService;
import com.ndovel.ebook.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
public class BookController {

    private BookService bookService;
    private SpiderService spiderService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public BookController(BookService bookService, SpiderService spiderService, UserService userService, PasswordEncoder passwordEncoder) {
        this.bookService = bookService;
        this.spiderService = spiderService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/book")
    public Response getAllBook(Integer index, Integer size){
        if(index==null || size==null || size<=0)
            return Response.success(bookService.getAllBook());
        else
            return Response.success(bookService.find(index, size));
    }

    @PostMapping("/book")
    public Response spider(String bookName, String authorName, String url, String token){

        if(bookName == null || authorName == null || url == null || token == null)
            return Response.error("信息不完整");

        BookDTO b = bookService.findExact(bookName, authorName).orElse(null);
        if (b!=null) {
            return Response.error("该书已存在");
        }

        List<User> users = userService.getUsers().stream()
                .filter(User::isEnabled)
                .collect(Collectors.toList());
        if (users.size()>0){
            User u = users.get(0);
            if (passwordEncoder.matches(token, u.getPassword())) {
                return Response.success(spiderService.spider(bookName, authorName, url , 0));
            }
        }
        return Response.error("身份验证失败");
    }


    @GetMapping("/find")
    public Response exactBook(Integer id){
        return Response.success(bookService.findOneById(id)
                .orElse(null));
    }

    @PostMapping("/find")
    public Response likeBook(String name){
        return Response.success(bookService.findByName(name));
    }

    @GetMapping("/visit")
    public Response visit(Integer bookId){
        if(bookId!=null && bookId>0){
            return Response.success(bookService.visit(bookId));
        }
        return Response.success(bookService.sumVisit());
    }
}
