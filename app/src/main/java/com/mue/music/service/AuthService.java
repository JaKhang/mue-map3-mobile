package com.mue.music.service;

import com.mue.music.model.AuthInfo;
import com.mue.music.model.request.LoginRequest;

public interface AuthService {
    boolean isAuthenticated();

    String getToken();

    void Login(LoginRequest loginRequest, ApiSuccessHandler<AuthInfo> successHandler);
}
