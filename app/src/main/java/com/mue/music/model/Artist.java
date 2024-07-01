package com.mue.music.model;

import com.mue.music.ui.adapter.home.CardItem;
import com.mue.music.ui.adapter.home.CartType;

import java.util.UUID;

import lombok.Data;

@Data
public class Artist implements CardItem {
    private UUID id;
    private String name;
    private String thumbnail;
    private int numberOfTracks;
    private boolean liked;

    @Override
    public CartType getType() {
        return CartType.ARTIST;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSubtitle() {
        return "";
    }
}
