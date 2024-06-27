package com.mue.music.service;

import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.ApiError;

public interface ApiFailureHandler {
    void onFailure(ApiError error);
}
