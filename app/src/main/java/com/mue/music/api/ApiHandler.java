package com.mue.music.api;

import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.ApiError;

public interface ApiHandler<T> {
    default void onSuccess(ApiBody<T> body){}

    default void onFailure(ApiError apiError){};

    default void onDone(){

    }

    static <T>ApiHandler<T> notDo(){
        return new ApiHandler<T>() {};
    }
}
