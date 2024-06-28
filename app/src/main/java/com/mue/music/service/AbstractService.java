package com.mue.music.service;

import com.google.gson.Gson;
import com.mue.music.model.Artist;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.ApiError;
import com.mue.music.model.domain.InfiniteList;

import java.io.IOException;
import java.util.logging.Handler;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
@RequiredArgsConstructor
public abstract class AbstractService {
    protected final ApiService apiService;

    protected ApiError extractError(Response<?> response) {
        assert response.errorBody() != null;
        try {
            return new Gson().fromJson(response.errorBody().string(), ApiError.class);
        } catch (IOException ignored) {
            return null;
        }
    }

    protected <T> void handleResponse(Response<ApiBody<T>> response, ApiHandler<T> handler) {
        if (response.isSuccessful()) {
            handler.onSuccess(response.body());
        } else {
            handler.onFailure(extractError(response));
        }
    }

    protected <T>void enqueue(Call<ApiBody<T>> call, ApiHandler<T> handler){
        call.enqueue(new Callback<ApiBody<T>>() {
            @Override
            public void onResponse(Call<ApiBody<T>> call, Response<ApiBody<T>> response) {
                handleResponse(response, handler);
                handler.onDone();
            }

            @Override
            public void onFailure(Call<ApiBody<T>> call, Throwable throwable) {
                handler.onFailure(null);
                handler.onDone();
            }
        });
    }


}
