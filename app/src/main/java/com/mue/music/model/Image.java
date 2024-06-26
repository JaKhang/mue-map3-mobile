package com.mue.music.model;

import java.util.UUID;

import lombok.Data;


@Data
public class Image {
    private UUID id;
    private int height;
    private int width;
    private String url;
    private String thumbnail;
}
