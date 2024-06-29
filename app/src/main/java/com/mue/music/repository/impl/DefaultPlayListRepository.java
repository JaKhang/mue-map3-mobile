package com.mue.music.repository.impl;

import com.mue.music.api.Api;
import com.mue.music.repository.PlayListRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultPlayListRepository implements PlayListRepository {
    private final Api API;
}
