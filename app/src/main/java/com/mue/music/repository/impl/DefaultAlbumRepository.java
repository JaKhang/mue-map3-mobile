package com.mue.music.repository.impl;

import com.mue.music.repository.AlbumRepository;
import com.mue.music.model.Album;
import com.mue.music.model.AlbumDetails;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;
import com.mue.music.repository.AbstractRepository;
import com.mue.music.api.ApiHandler;
import com.mue.music.api.Api;

import java.util.UUID;

import retrofit2.Call;

public class DefaultAlbumRepository extends AbstractRepository implements AlbumRepository {
    public DefaultAlbumRepository(Api API) {
        super(API);
    }

    @Override
    public void findAll(PageRequest pageRequest, ApiHandler<InfiniteList<Album>> handler) {
        Call<ApiBody<InfiniteList<Album>>> call = api.findAlbums(pageRequest.getPage(), pageRequest.size, pageRequest.toSortString(), null);
        enqueue(call, handler);
    }

    @Override
    public void findById(UUID id, ApiHandler<AlbumDetails> handler) {
        Call<ApiBody<AlbumDetails>> call = api.findAlbumById(id.toString());
        enqueue(call, handler);
    }

    @Override
    public void searchByNameAndArtistAndTrack(String keyword, PageRequest pageRequest, ApiHandler<InfiniteList<Album>> handler) {
        Call<ApiBody<InfiniteList<Album>>> call = api.searchByNameOrArtistOrTrack(pageRequest.getPage(), pageRequest.getSize(), pageRequest.toSortString(), keyword);
        enqueue(call, handler);
    }

    @Override
    public void findByArtistId(UUID artistId, PageRequest pageRequest, String query, ApiHandler<InfiniteList<Album>> handler) {
        Call<ApiBody<InfiniteList<Album>>> call = api.findAlbumsByArtistId(artistId, pageRequest.getPage(), pageRequest.getSize(), pageRequest.toSortString(), query);
        enqueue(call, handler);
    }

    @Override
    public void findByGenreId(UUID uuid, PageRequest pageRequest, ApiHandler<InfiniteList<Album>> handler) {
        Call<ApiBody<InfiniteList<Album>>> call = api.findAlbumsByGenreId(uuid, pageRequest.getPage(), pageRequest.getSize(), pageRequest.toSortString());
        enqueue(call, handler);
    }


}
