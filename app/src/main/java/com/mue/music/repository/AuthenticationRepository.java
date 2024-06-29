package com.mue.music.repository;

import com.mue.music.api.ApiHandler;
import com.mue.music.model.Principal;
import com.mue.music.model.request.LoginRequest;
import com.mue.music.model.request.RegisterRequest;

import java.util.UUID;

public interface AuthenticationRepository {

    void login(LoginRequest loginRequest, ApiHandler<Principal> handler);

    void LoginWithLocalToken(ApiHandler<Principal> handler);

    void register(RegisterRequest registerRequest, ApiHandler<UUID> handler);
}
