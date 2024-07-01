package com.mue.music.repository;

import com.mue.music.api.ApiHandler;
import com.mue.music.model.Genre;

import java.util.List;

public interface GenreRepository {
    void findAll(ApiHandler<List<Genre>> handler);
}
