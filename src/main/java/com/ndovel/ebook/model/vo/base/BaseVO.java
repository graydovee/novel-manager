package com.ndovel.ebook.model.vo.base;

import com.ndovel.ebook.model.vo.Response;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public abstract class BaseVO<T> implements Response {

    private Integer code;

    private String message;

    private T data;

    public BaseVO(HttpStatus status, T data){
        this.code = status.value();
        this.message = status.getReasonPhrase();
        this.data = data;
    }
}
