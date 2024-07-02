package com.mue.music.model;

import static com.mue.music.util.CommonUtils.getArtistDetailNames;
import static com.mue.music.util.CommonUtils.getArtistNames;

import com.mue.music.model.enums.AlbumType;
import com.mue.music.ui.adapter.detail.ItemDetail;
import com.mue.music.ui.adapter.detail.TypeDetail;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import lombok.Data;


@Data
public class AlbumDetails implements ItemDetail {
    private UUID id;
    private String alias;
    private String description;
    private String shortDescription;
    private String thumbnail;
    private Image image;
    private AlbumType type;
    private Timestamp createdAt;
    private Timestamp lastModifiedAt;
    private SimpleObject distributor;
    private int numberOfTrack;
    private int duration;
    private boolean liked;
    private List<Artist> artists;
    private List<Track> tracks;

    @Override
    public String getDetailName() {
        return "";
    }

    @Override
    public String getFollowersOrArtists() {
        return getArtistDetailNames(artists);
    }

    @Override
    public List<Artist> getArtistDetails() {
        return artists;
    }

    @Override
    public List<TrackDetails> getTrackDetails() {
        return Collections.emptyList();
    }

    @Override
    public String getImageUrl() {
        return thumbnail;
    }

    @Override
    public TypeDetail getTypeDetail() {
        return TypeDetail.ALBUM;
    }
}
