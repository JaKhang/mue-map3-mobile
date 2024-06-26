package com.mue.music.model.domain;

import lombok.Data;

@Data
public class ApiBody<T> {
    private String message;

    private T data;


    public ApiBody(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ApiBody(T data) {
        this.data = data;
    }
}
