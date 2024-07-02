package com.mue.music.repository.impl;

import com.mue.music.api.Api;
import com.mue.music.model.Artist;
import com.mue.music.model.ArtistDetails;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;
import com.mue.music.repository.AbstractRepository;
import com.mue.music.api.ApiHandler;
import com.mue.music.repository.ArtistRepository;

import java.util.UUID;

import retrofit2.Call;

public class DefaultArtistRepository extends AbstractRepository implements ArtistRepository {

    public DefaultArtistRepository(Api API) {
        super(API);
    }

    @Override
    public void findAll(PageRequest pageRequest, String query, ApiHandler<InfiniteList<Artist>> handler) {
        Call<ApiBody<InfiniteList<Artist>>> call = api.findArtists(pageRequest.getPage(), pageRequest.size, pageRequest.toSortString(),    query);
        enqueue(call, handler);
    }

    @Override
    public void findById(UUID id, ApiHandler<ArtistDetails> handler) {
        Call<ApiBody<ArtistDetails>> call = api.findAristById(id);
        enqueue(call, handler);
    }

    @Override
    public void searchByName(String name, int limit, ApiHandler<InfiniteList<Artist>> handler) {
        String query = String.format("%s:%s","name", name);
        Call<ApiBody<InfiniteList<Artist>>> call = api.findArtists(0, limit,null, query);
        enqueue(call, handler);
    }




}
