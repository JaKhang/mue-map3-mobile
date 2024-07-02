package com.mue.music.model;

import com.mue.music.model.enums.ArtistType;
import com.mue.music.ui.adapter.detail.ItemDetail;
import com.mue.music.ui.adapter.detail.TypeDetail;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ArtistDetails implements ItemDetail {
    private UUID id;
    private String thumbnail;
    private String avatarUrl;
    private String coverUrl;
    private String name;
    private String countryName;
    private Date birthday;
    private Integer likes;
    private String description;
    private List<String> typeNames;
    private List<ArtistType> types;
    private SimpleObject country;
    private Image coverImage;
    private Image avatarImage;
    private Timestamp createdAt;
    private Timestamp lastModifiedAt;
    private boolean liked;

    @Override
    public String getDetailName() {
        return name;
    }

    @Override
    public String getFollowersOrArtists() {
        return likes + " followers";
    }

    @Override
    public List<Artist> getArtistDetails() {
        return Collections.emptyList();
    }

    @Override
    public List<TrackDetails> getTrackDetails() {
        return Collections.emptyList();
    }

    @Override
    public String getImageUrl() {
        return avatarUrl;
    }

    @Override
    public TypeDetail getTypeDetail() {
        return TypeDetail.ARTIST;
    }
}
