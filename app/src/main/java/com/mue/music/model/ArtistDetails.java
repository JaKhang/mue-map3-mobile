package com.mue.music.model;

import com.mue.music.model.enums.ArtistType;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ArtistDetails {
    private UUID id;
    private String thumbnail;
    private String alias;
    private String avatarUrl;
    private String coverUrl;
    private String name;
    private String countryName;
    private Date birthday;
    private Integer likes;
    private String description;
    private List<String> typeNames;
    private List<ArtistType> types;
    private SimpleObject country;
    private Image coverImage;
    private Image avatarImage;
    private Timestamp createdAt;
    private Timestamp lastModifiedAt;
    private boolean liked;
}
