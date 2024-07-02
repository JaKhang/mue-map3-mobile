package com.mue.music.model;

import com.mue.music.ui.adapter.home.CardItem;
import com.mue.music.ui.adapter.home.CardType;

import java.util.UUID;

import lombok.Data;

@Data
public class Artist implements CardItem {
    private UUID id;
    private String name;
    private String thumbnail;
    private int numberOfTracks;
    private boolean liked;

    public Artist() {
    }

    public Artist(String name, String thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    @Override
    public CardType getCardType() {
        return CardType.ARTIST;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSubtitle() {
        return "Artists";
    }

    @Override
    public String getImageUrl() {
        return "";
    }
}
