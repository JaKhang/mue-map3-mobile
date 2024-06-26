package com.mue.music.model;

import java.util.UUID;

import lombok.Data;


@Data
public class SimpleObject {
    private UUID id;
    private String name;
    private String alias;
}
