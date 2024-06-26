package com.mue.music.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class TrackDetails {
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
}
