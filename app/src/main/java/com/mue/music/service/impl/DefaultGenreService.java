package com.mue.music.service.impl;

import com.mue.music.service.ApiService;
import com.mue.music.service.GenreService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultGenreService implements GenreService {
    private final ApiService apiService;

}
