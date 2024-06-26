package com.mue.music.model;

import com.mue.music.model.enums.AlbumType;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import lombok.Data;


@Data
public class AlbumDetails {
    private UUID id;
    private String alias;
    private String name;
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
    private List<Artist> tracks;
}
