package com.mue.music.model;

import java.util.UUID;

import lombok.Data;

@Data
public class Artist {
    private UUID id;
    private String name;
    private String alias;
    private String thumbnail;
    private int numberOfTracks;
    private int subscribes;
    private boolean liked;
    private boolean subscribed;
}
