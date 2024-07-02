package com.mue.music.repository.impl;

import com.mue.music.api.Api;
import com.mue.music.model.Track;
import com.mue.music.model.TrackDetails;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;
import com.mue.music.model.enums.Bitrate;
import com.mue.music.repository.AbstractRepository;
import com.mue.music.api.ApiHandler;
import com.mue.music.repository.TrackRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import retrofit2.Call;

public class DefaultTrackRepository extends AbstractRepository implements TrackRepository {
    public DefaultTrackRepository(Api API) {
        super(API);
    }

    @Override
    public void getStreamingUrl(UUID id, ApiHandler<Map<Bitrate, String>> handler) {
        Call<ApiBody<Map<Bitrate, String>>> call = api.getStreamingUrl(id);
        enqueue(call, handler);
    }

    @Override
    public void findTrackById(UUID id, ApiHandler<TrackDetails> handler) {

    }

    @Override
    public void findAllByAlbumId(UUID artistId, ApiHandler<List<Track>> handler) {

    }

    @Override
    public void search(String keyword, int limit, ApiHandler<InfiniteList<Track>> handler) {
        Call<ApiBody<InfiniteList<Track>>> call = api.findTracks("name:" + keyword, limit);
        enqueue(call, handler);
    }
}
