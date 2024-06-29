package com.mue.music.repository.impl;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.mue.music.api.Api;
import com.mue.music.repository.AuthenticationRepository;
import com.mue.music.constants.Key;
import com.mue.music.constants.PreferenceName;
import com.mue.music.model.AuthInfo;
import com.mue.music.model.Principal;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.ApiError;
import com.mue.music.model.request.LoginRequest;
import com.mue.music.model.request.RegisterRequest;
import com.mue.music.repository.AbstractRepository;
import com.mue.music.api.ApiHandler;
import com.mue.music.feature.auth.AuthenticationManger;

import java.util.UUID;

import retrofit2.Call;

public class DefaultAuthRepository extends AbstractRepository implements AuthenticationRepository {
    private final AuthenticationManger authenticationManger;
    private final Application application;
    private final SharedPreferences sharedPreferences;

    public DefaultAuthRepository(Api API, Application application, AuthenticationManger authenticationManger) {
        super(API);
        this.application = application;
        this.sharedPreferences = application.getSharedPreferences(PreferenceName.LOCAL, Context.MODE_PRIVATE);
        this.authenticationManger = authenticationManger;

    }


    @Override
    public void login(LoginRequest loginRequest, ApiHandler<Principal> handler) {
        Call<ApiBody<AuthInfo>> call = api.authenticate(loginRequest);
        enqueue(call, new ApiHandler<AuthInfo>() {
            @Override
            public void onSuccess(ApiBody<AuthInfo> body) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Key.ACCESS_TOKEN, body.getData().getToken());
                editor.apply();
                authenticationManger.setToken(body.getData().getToken());
                loadPrincipal(handler);
            }

            @Override
            public void onFailure(ApiError apiError) {
                handler.onFailure(apiError);
                onDone();
            }
        });
    }

    private void loadPrincipal(ApiHandler<Principal> handler) {
        Call<ApiBody<Principal>> call = api.getPrincipal();
        enqueue(call, new ApiHandler<Principal>() {
            @Override
            public void onSuccess(ApiBody<Principal> body) {
                handler.onSuccess(body);
                handler.onDone();
                authenticationManger.setPrincipal(body.getData());
            }

            @Override
            public void onFailure(ApiError apiError) {
                handler.onFailure(apiError);
                handler.onDone();
                authenticationManger.setToken(null);
            }
        });
    }

    @Override
    public void LoginWithLocalToken(ApiHandler<Principal> handler) {
        String token = sharedPreferences.getString(Key.ACCESS_TOKEN, null);
        authenticationManger.setToken(token);
        loadPrincipal(handler);
    }

    @Override
    public void register(RegisterRequest registerRequest, ApiHandler<UUID> handler) {
        Call<ApiBody<UUID>> call = api.register(registerRequest);
        enqueue(call, handler);
    }
}
