package com.mue.music.ui.adapter.home;

import java.io.Serializable;
import java.util.UUID;

public interface CardItem extends Serializable {
    String getThumbnail();
    CardType getCardType();
    String getTitle();
    String getSubtitle();
    UUID getId();
    String getImageUrl();
}
