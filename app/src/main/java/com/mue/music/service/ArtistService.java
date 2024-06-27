package com.mue.music.service;

import com.mue.music.model.Artist;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.ApiError;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;

public interface ArtistService {

    void findAll(PageRequest pageRequest, ApiHandler<InfiniteList<Artist>> handler);

    void findById(ApiSuccessHandler<ApiBody<InfiniteList<Artist>>> onSuccess, ApiSuccessHandler<ApiError> onFailure);


}
