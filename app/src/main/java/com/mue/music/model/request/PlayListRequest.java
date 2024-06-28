package com.mue.music.model.request;


import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PlayListRequest {
    private String name;
    private String alias;
    private UUID imageId;
    private String description;
    private boolean isPublic;
}
