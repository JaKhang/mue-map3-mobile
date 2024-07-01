package com.mue.music.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class Genre implements Serializable{
    private UUID id;
    private String name;
    private String thumbnail;
    private String color;
}
