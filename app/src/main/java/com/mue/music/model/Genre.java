package com.mue.music.model;

import java.util.UUID;

import lombok.Data;

@Data
public class Genre {
    private UUID id;
    private String name;
    private String alias;
    private Image imageResponse;
    private String thumbnail;
    private String color;
}
