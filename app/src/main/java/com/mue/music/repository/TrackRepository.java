package com.mue.music.repository;

import com.mue.music.api.ApiHandler;
import com.mue.music.model.Track;
import com.mue.music.model.TrackDetails;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.enums.Bitrate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TrackRepository {
    void getStreamingUrl(UUID id, ApiHandler<Map<Bitrate, String>> handler);

    void findTrackById(UUID id, ApiHandler<TrackDetails> handler);

    void findAllByAlbumId(UUID artistId, ApiHandler<List<Track>> handler);

    void search(String keyword, int limit, ApiHandler<InfiniteList<Track>> handler);

}
