package com.mue.music.model;

import lombok.Data;

@Data
public class AuthInfo {
    private String token;
    private String type;
    private boolean isVerified;
}
