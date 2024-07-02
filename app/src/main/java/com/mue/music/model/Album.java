package com.mue.music.model;

import static com.mue.music.util.CommonUtils.getArtistNames;

import com.mue.music.model.enums.AlbumType;
import com.mue.music.ui.adapter.home.CardItem;
import com.mue.music.ui.adapter.home.CardType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Album implements CardItem {
    private UUID id;
    private String name;
    private String alias;
    private AlbumType type;
    private String thumbnail;
    private List<SimpleObject> artists;
    private List<SimpleObject> genres;
    private Integer numberOfTrack;
    private Date releaseDate;
    private String shortDescription;
    private Integer duration;
    private boolean liked;

    public Album() {}

    public Album(String name, String thumbnail, AlbumType type, List<SimpleObject> artists) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.type = type;
        this.artists = artists;
    }

    @Override
    public String getTitle() {
        return name;
    }



    @Override
    public String getSubtitle() {
        return type.name() + " | " + getArtistNames(artists);
    }

    @Override
    public String getImageUrl() {
        return "";
    }

    @Override
    public String getFollowersOrArtists() {
        return "";
    }

    public CardType getCardType() {
        return CardType.ALBUM;
    }
}
