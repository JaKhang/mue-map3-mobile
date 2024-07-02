package com.mue.music.model;

import static com.mue.music.util.CommonUtils.getArtistDetailNames;

import com.mue.music.model.enums.AlbumType;
import com.mue.music.ui.adapter.detail.TypeDetail;
import com.mue.music.ui.adapter.home.CardItem;
import com.mue.music.ui.adapter.home.CardType;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import lombok.Data;


@Data
public class AlbumDetails implements CardItem {
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
    public CardType getCardType() {
        return CardType.ARTIST;
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

    @Override
    public String getFollowersOrArtists() {
        return getArtistDetailNames(artists);
    }
}
