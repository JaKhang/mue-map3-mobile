package com.mue.music.service.impl;

import com.mue.music.model.Principal;
import com.mue.music.service.AuthenticationManger;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class DefaultAuthenticationManger implements AuthenticationManger {
    private String token;
    public Principal principal;

    @Override
    public boolean isAuthenticated() {
        return principal != null && token != null;
    }
}
