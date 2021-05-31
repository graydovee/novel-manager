package com.ndovel.novel.spider.remote.model;

import lombok.Data;

/**
 * @author graydove
 */
@Data
public class Response<T> {

    private T data;

    private int code;

    private String message;

}
