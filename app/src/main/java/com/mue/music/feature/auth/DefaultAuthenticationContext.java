package com.mue.music.feature.auth;

import com.mue.music.model.Principal;

import lombok.Data;


@Data
public class DefaultAuthenticationContext implements AuthenticationContext {
    private String token;
    public Principal principal;

    @Override
    public boolean isAuthenticated() {
        return principal != null && token != null;
    }
}
