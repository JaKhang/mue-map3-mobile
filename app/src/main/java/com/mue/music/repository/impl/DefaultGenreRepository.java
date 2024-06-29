package com.mue.music.repository.impl;

import com.mue.music.api.Api;
import com.mue.music.repository.GenreRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultGenreRepository implements GenreRepository {
    private final Api API;

}
