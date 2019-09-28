package com.ndovel.ebook.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode
public class Response {

    private Integer code;

    private String message;

    private Object data;

    private Response(HttpStatus status, Object data){
        this.code = status.value();
        this.message = status.getReasonPhrase();
        this.data = data;
    }

    public static Response success(Object entity){
        return new Response(HttpStatus.OK, entity);
    }

    public static Response error(Object entity){
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, entity);
    }

}
