package com.mue.music.service;

import com.mue.music.model.Principal;
import com.mue.music.model.request.LoginRequest;
import com.mue.music.model.request.RegisterRequest;

import java.util.UUID;

public interface AuthService {

    void login(LoginRequest loginRequest, ApiHandler<Principal> handler);

    void LoginWithLocalToken(ApiHandler<Principal> handler);

    void register(RegisterRequest registerRequest, ApiHandler<UUID> handler);
}
