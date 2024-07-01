package com.mue.music.model;

import java.util.UUID;

import lombok.Data;

@Data
public class Artist {
    private UUID id;
    private String name;
    private String thumbnail;
    private int numberOfTracks;
    private boolean liked;
}
