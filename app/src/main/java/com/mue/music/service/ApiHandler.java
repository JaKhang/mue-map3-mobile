package com.mue.music.service;

import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.ApiError;

public interface ApiHandler<T> {
    void onSuccess(ApiBody<T> body);

    void onFailure(ApiError apiError);
}
