package com.mue.music.model;

import com.mue.music.model.enums.AlbumType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Album {
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
}
