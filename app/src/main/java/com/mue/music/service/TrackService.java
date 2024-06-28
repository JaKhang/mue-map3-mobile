package com.mue.music.service;

import com.mue.music.model.Track;
import com.mue.music.model.TrackDetails;
import com.mue.music.model.enums.Bitrate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TrackService {
    void getStreamingUrl(UUID id, ApiHandler<Map<Bitrate, String>> handler);

    void findTrackById(UUID id, ApiHandler<TrackDetails> handler);

    void findAllByAlbumId(UUID artistId, ApiHandler<List<Track>> handler);

}
