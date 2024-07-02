package com.mue.music.ui.adapter.detail;

import com.mue.music.model.Artist;
import com.mue.music.model.Track;
import com.mue.music.model.TrackDetails;

import java.io.Serializable;
import java.util.List;

public interface ItemDetail extends Serializable {
    String getDetailName();
    String getFollowersOrArtists();
    List<Artist> getArtistDetails();
    List<TrackDetails> getTrackDetails();
    String getImageUrl();
    TypeDetail getTypeDetail();
}
