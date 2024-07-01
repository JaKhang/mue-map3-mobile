package com.mue.music.model_test_ui;

import com.mue.music.model_test_ui.abs.BaseApiModel;

public class Track extends BaseApiModel {
    private String artist;
    private long categoryId;
    private String imageUrl;

    public Track() {
    }

    public Track(String id, String name) {
        super(id, name);
    }

    public Track(String id, long categoryId, String name, String artist, String imageUrl) {
        super(id, name);
        this.artist = artist;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
