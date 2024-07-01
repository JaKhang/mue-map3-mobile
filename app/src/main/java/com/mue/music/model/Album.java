package com.mue.music.model;

import static com.mue.music.util.CommonUtils.getArtistNames;

import com.mue.music.model.enums.AlbumType;
import com.mue.music.ui.adapter.home.CardItem;
import com.mue.music.ui.adapter.home.CartType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;

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

    @Override
    public String getTitle() {
        return name;
    }



    @Override
    public String getSubtitle() {
        return type.name() + " - " + getArtistNames(artists);
    }

    public CartType getType() {
        return CartType.ALBUM;

    }
}
