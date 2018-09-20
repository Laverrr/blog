package com.laver.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by L on 2018/9/16.
 */
@Getter
@Setter
public class Response {
    private boolean success;
    private String message;
    private Object body;

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Response(boolean success, String message, Object boday) {
        this.success = success;
        this.message = message;
        this.body = boday;
    }
}
