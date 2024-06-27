package com.mue.music.service.impl;

import com.mue.music.service.ApiService;
import com.mue.music.service.PlayListService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultPlayListService implements PlayListService {
    private final ApiService apiService;
}
