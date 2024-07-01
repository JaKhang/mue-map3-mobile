package com.mue.music.model;

import java.util.UUID;

import lombok.Data;


@Data
public class Distributor {
    private UUID id;
    private String name;
    private String thumbnail;
    private Image image;
}
