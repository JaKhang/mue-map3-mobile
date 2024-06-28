package com.mue.music.service.impl;

import com.mue.music.model.Track;
import com.mue.music.model.TrackDetails;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.enums.Bitrate;
import com.mue.music.service.AbstractService;
import com.mue.music.service.ApiHandler;
import com.mue.music.service.ApiService;
import com.mue.music.service.TrackService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import retrofit2.Call;

public class DefaultTrackService extends AbstractService implements TrackService {
    public DefaultTrackService(ApiService apiService) {
        super(apiService);
    }

    @Override
    public void getStreamingUrl(UUID id, ApiHandler<Map<Bitrate, String>> handler) {
        Call<ApiBody<Map<Bitrate, String>>> call = apiService.getStreamingUrl(id);
        enqueue(call, handler);
    }

    @Override
    public void findTrackById(UUID id, ApiHandler<TrackDetails> handler) {

    }

    @Override
    public void findAllByAlbumId(UUID artistId, ApiHandler<List<Track>> handler) {

    }
}
