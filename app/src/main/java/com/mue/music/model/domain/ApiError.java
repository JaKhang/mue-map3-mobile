package com.mue.music.model.domain;

import lombok.Data;

@Data
public class ApiError {
    private String message;
    private String code;
    private int status;
}
