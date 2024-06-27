package com.mue.music.service.impl;

import com.google.gson.Gson;
import com.mue.music.model.Artist;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.ApiError;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;
import com.mue.music.service.ApiFailureHandler;
import com.mue.music.service.ApiHandler;
import com.mue.music.service.ApiService;
import com.mue.music.service.ApiSuccessHandler;
import com.mue.music.service.ArtistService;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiredArgsConstructor
public class DefaultArtistService implements ArtistService {
    private final ApiService apiService;


    @Override
    public void findAll(PageRequest pageRequest, ApiHandler<InfiniteList<Artist>> handler) {
        Call<ApiBody<InfiniteList<Artist>>> call = apiService.findArtists(pageRequest.getPage(), pageRequest.size, pageRequest.toSortString(), null);
        call.enqueue(new Callback<ApiBody<InfiniteList<Artist>>>() {
            @Override
            public void onResponse(Call<ApiBody<InfiniteList<Artist>>> call, Response<ApiBody<InfiniteList<Artist>>> response) {
                if (response.isSuccessful()) {
                    handler.onSuccess(response.body());
                } else {
                    assert response.errorBody() != null;
                    try {
                        ApiError apiError = new Gson().fromJson(response.errorBody().string(), ApiError.class);
                        handler.onFailure(apiError);
                    } catch (IOException ignored) {
                        handler.onFailure(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiBody<InfiniteList<Artist>>> call, Throwable throwable) {
                handler.onFailure(null);
            }
        });
    }

    @Override
    public void findById(ApiSuccessHandler<ApiBody<InfiniteList<Artist>>> onSuccess, ApiSuccessHandler<ApiError> onFailure) {

    }
}
