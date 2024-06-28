package com.mue.music.service;

import com.mue.music.model.Artist;
import com.mue.music.model.ArtistDetails;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.ApiError;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;

import java.util.UUID;

public interface ArtistService {

    void findAll(PageRequest pageRequest, ApiHandler<InfiniteList<Artist>> handler);

    void findById(UUID uuid, ApiHandler<ArtistDetails> handler);

    void searchByName(String name, int limit, ApiHandler<InfiniteList<Artist>> handler);
}
