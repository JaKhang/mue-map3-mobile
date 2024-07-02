package com.mue.music.model;

import com.mue.music.model.enums.ArtistType;
import com.mue.music.ui.adapter.detail.TypeDetail;
import com.mue.music.ui.adapter.home.CardItem;
import com.mue.music.ui.adapter.home.CardType;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ArtistDetails implements CardItem {
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
    public CardType getCardType() {
        return null;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSubtitle() {
        return "";
    }

    @Override
    public String getImageUrl() {
        return "";
    }

    @Override
    public String getFollowersOrArtists() {
        return likes + " followers";
    }
}
