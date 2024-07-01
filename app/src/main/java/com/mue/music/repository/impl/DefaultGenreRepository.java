package com.mue.music.repository.impl;

import com.mue.music.api.Api;
import com.mue.music.api.ApiHandler;
import com.mue.music.model.Genre;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.repository.AbstractRepository;
import com.mue.music.repository.GenreRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;

public class DefaultGenreRepository extends AbstractRepository implements GenreRepository {

    public DefaultGenreRepository(Api api) {
        super(api);
    }

    @Override
    public void findAll(ApiHandler<List<Genre>> handler) {
        Call<ApiBody<List<Genre>>> call = api.findGenres();
        enqueue(call, handler);
    }
}
