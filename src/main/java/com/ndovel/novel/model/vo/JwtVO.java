package com.ndovel.novel.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtVO {

    private String token;

    private String prefix;

}
