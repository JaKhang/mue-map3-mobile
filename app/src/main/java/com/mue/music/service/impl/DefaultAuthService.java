package com.mue.music.service.impl;

import com.mue.music.model.AuthInfo;
import com.mue.music.model.request.LoginRequest;
import com.mue.music.service.ApiService;
import com.mue.music.service.ApiSuccessHandler;
import com.mue.music.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {
    private final ApiService apiService;

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public String getToken() {
        return "";
    }

    @Override
    public void Login(LoginRequest loginRequest, ApiSuccessHandler<AuthInfo> successHandler) {

    }
}
