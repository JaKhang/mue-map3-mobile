package com.mue.music.model;

import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class SimpleObject {
    private UUID id;
    private String name;

    public SimpleObject() {}

    public SimpleObject(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
