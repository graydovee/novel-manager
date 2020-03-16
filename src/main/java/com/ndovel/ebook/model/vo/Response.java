package com.ndovel.ebook.model.vo;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

public interface Response {

    static Response success(){
        return success("OK");
    }

    static Response success(Object entity){
        return pack(HttpStatus.OK, entity);
    }

    static Response error(){
        return error("ERROR");
    }

    static Response error(Object entity){
        return pack(HttpStatus.INTERNAL_SERVER_ERROR, entity);
    }

    @SuppressWarnings("unchecked")
    static Response pack(HttpStatus status, Object data){
        if (data instanceof Page) {
            return new PageVO(status, (Page) data);
        }
        return new DefaultVO(status, data);
    }

}
