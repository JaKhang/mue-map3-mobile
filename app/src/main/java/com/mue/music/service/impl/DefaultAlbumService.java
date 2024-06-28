package com.mue.music.service.impl;

import com.mue.music.model.Album;
import com.mue.music.model.AlbumDetails;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;
import com.mue.music.service.AbstractService;
import com.mue.music.service.AlbumService;
import com.mue.music.service.ApiHandler;
import com.mue.music.service.ApiService;

import java.util.UUID;

import retrofit2.Call;

public class DefaultAlbumService extends AbstractService implements AlbumService {
    public DefaultAlbumService(ApiService apiService) {
        super(apiService);
    }

    @Override
    public void findAll(PageRequest pageRequest, ApiHandler<InfiniteList<Album>> handler) {
        Call<ApiBody<InfiniteList<Album>>> call = apiService.findAlbums(pageRequest.getPage(), pageRequest.size, pageRequest.toSortString(), null);
        enqueue(call, handler);
    }

    @Override
    public void findById(UUID id, ApiHandler<AlbumDetails> handler) {
        Call<ApiBody<AlbumDetails>> call = apiService.findAlbumById(id);
        enqueue(call, handler);
    }

    @Override
    public void searchByNameAndArtistAndTrack(String keyword, PageRequest pageRequest, ApiHandler<InfiniteList<Album>> handler) {
        Call<ApiBody<InfiniteList<Album>>> call = apiService.searchByNameOrArtistOrTrack(pageRequest.getPage(), pageRequest.getSize(), pageRequest.toSortString(), keyword);
        enqueue(call, handler);
    }

    @Override
    public void findByArtistId(UUID artistId, PageRequest pageRequest, String query, ApiHandler<InfiniteList<Album>> handler) {
        Call<ApiBody<InfiniteList<Album>>> call = apiService.findAlbumByArtistId(artistId, pageRequest.getPage(), pageRequest.getSize(), pageRequest.toSortString(), query);
        enqueue(call, handler);
    }


}
