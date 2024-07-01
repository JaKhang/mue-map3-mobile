package com.mue.music.ui.adapter.home;

import java.util.UUID;

public interface CardItem {
    String getThumbnail();
    CartType getType();
    String getTitle();
    String getSubtitle();
    UUID getId();
}
