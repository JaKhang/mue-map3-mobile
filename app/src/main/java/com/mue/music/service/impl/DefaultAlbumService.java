package com.mue.music.service.impl;

import com.mue.music.service.AlbumService;
import com.mue.music.service.ApiService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultAlbumService implements AlbumService {
    private final  ApiService apiService;
}
