package com.mue.music.service.impl;

import com.mue.music.service.ApiService;

import javax.inject.Inject;

public class DefaultArtistService {
    private final ApiService apiService;

    @Inject
    public DefaultArtistService(ApiService apiService) {
        this.apiService = apiService;
    }
}
