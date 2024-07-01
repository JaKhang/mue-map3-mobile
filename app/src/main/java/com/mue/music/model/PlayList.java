package com.mue.music.model;

import java.util.UUID;

import lombok.Data;

@Data
public class PlayList {
    private UUID id;
    private String name;
    private String owner;
    private String thumbnail;
    private Image image;
    private Integer totalTrack;
    private Boolean liked;
    private Integer likes;
}
