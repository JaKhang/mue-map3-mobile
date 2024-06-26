package com.mue.music.model;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Track {
    private UUID id;
    private String name;
    private String alias;
    private String thumbnail;
    private SimpleObject album;
    private List<SimpleObject> artists;
    private List<SimpleObject> genres;
    private int duration;
    private boolean liked;
}
