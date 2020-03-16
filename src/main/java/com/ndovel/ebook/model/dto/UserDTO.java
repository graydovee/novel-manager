package com.ndovel.ebook.model.dto;

import com.ndovel.ebook.model.dto.base.BaseDTO;
import com.ndovel.ebook.model.entity.Authority;
import com.ndovel.ebook.model.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO implements BaseDTO<UserDTO, User> {

    private Integer id;

    private String username;

    private String password;

    private Set<Authority> authorities = new HashSet<>();

    private Date createTime;

    private Date updateTime;

}
