package com.mue.music.model;

import com.mue.music.ui.adapter.home.CardItem;
import com.mue.music.ui.adapter.home.CardType;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TrackDetails implements CardItem {
    private UUID id;
    private String name;
    private String alias;
    private List<Artist> artists;
    private List<SimpleObject> genres;
    private Timestamp createdAt;
    private Timestamp lastModifiedAt;
    private Date releaseDate;
    private SimpleObject distributor;
    private SimpleObject composer;
    private String lyrics;
    private String description;
    private Image image;
    private SimpleObject album;
    private String mvLink;
    private Boolean isIndie;
    private Integer duration;
    private Boolean isOfficial;
    private Integer index;
    private Boolean isLike;


    @Override
    public String getFollowersOrArtists() {
        return "";
    }

    @Override
    public String getThumbnail() {
        return "";
    }

    @Override
    public CardType getCardType() {
        return null;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getSubtitle() {
        return "";
    }

    @Override
    public String getImageUrl() {
        return "";
    }
}
