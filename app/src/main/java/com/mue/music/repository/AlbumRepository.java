package com.mue.music.repository;

import com.mue.music.api.ApiHandler;
import com.mue.music.model.Album;
import com.mue.music.model.AlbumDetails;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;

import java.util.UUID;

public interface AlbumRepository {

    void findAll(PageRequest pageRequest, ApiHandler<InfiniteList<Album>> handler);

    void findById(UUID id, ApiHandler<AlbumDetails> handler);

    void searchByNameAndArtistAndTrack(String keyword, PageRequest pageRequest, ApiHandler<InfiniteList<Album>> handler);

    void findByArtistId(UUID artistId, PageRequest pageRequest, String query, ApiHandler<InfiniteList<Album>> handler);

    void findByGenreId(UUID uuid, PageRequest releaseDate, ApiHandler<InfiniteList<Album>> apiHandler);
}
