package com.mue.music.service.impl;

import com.mue.music.model.Artist;
import com.mue.music.model.ArtistDetails;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;
import com.mue.music.service.AbstractService;
import com.mue.music.service.ApiHandler;
import com.mue.music.service.ApiService;
import com.mue.music.service.ArtistService;

import java.util.UUID;

import retrofit2.Call;

public class DefaultArtistService extends AbstractService implements ArtistService {

    public DefaultArtistService(ApiService apiService) {
        super(apiService);
    }

    @Override
    public void findAll(PageRequest pageRequest, ApiHandler<InfiniteList<Artist>> handler) {
        Call<ApiBody<InfiniteList<Artist>>> call = apiService.findArtists(pageRequest.getPage(), pageRequest.size, pageRequest.toSortString(), null);
        enqueue(call, handler);
    }

    @Override
    public void findById(UUID id, ApiHandler<ArtistDetails> handler) {
        Call<ApiBody<ArtistDetails>> call = apiService.findAristById(id);
        enqueue(call, handler);
    }

    @Override
    public void searchByName(String name, int limit, ApiHandler<InfiniteList<Artist>> handler) {
        String query = String.format("%s:%s","name", name);
        Call<ApiBody<InfiniteList<Artist>>> call = apiService.findArtists(0, limit,null, query);
        enqueue(call, handler);
    }




}
