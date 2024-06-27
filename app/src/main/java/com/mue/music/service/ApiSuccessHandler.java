package com.mue.music.service;

import com.mue.music.model.Artist;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.InfiniteList;

public interface ApiSuccessHandler<T> {
    void onSuccess(ApiBody<InfiniteList<Artist>> data);
}
