package com.mue.music.model;

import com.mue.music.ui.adapter.home.CardItem;
import com.mue.music.ui.adapter.home.CardType;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Track implements CardItem {
    private UUID id;
    private String name;
    private String alias;
    private String thumbnail;
    private SimpleObject album;
    private Image image;
    private List<SimpleObject> artists;
    private List<SimpleObject> genres;
    private int duration;
    private boolean liked;

    public Track() {}

    public Track(String name, String thumbnail, List<SimpleObject> artists) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.artists = artists;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(id, track.id);
    }

    public String getArtistsName() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (SimpleObject artist : artists){
            stringJoiner.add(artist.getName());
        }
        return stringJoiner.toString();
    }

    @Override
    public CardType getCardType() {
        return CardType.TRACK;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSubtitle() {
        return getArtistsName();
    }

    @Override
    public String getImageUrl() {
        return "";
    }

    @Override
    public String getFollowersOrArtists() {
        return "";
    }
}
