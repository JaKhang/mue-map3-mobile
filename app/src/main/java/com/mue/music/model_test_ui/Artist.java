package com.mue.music.model_test_ui;

import com.mue.music.model_test_ui.abs.BaseApiModel;

import java.util.List;

public class Artist extends BaseApiModel {
    private List<Track> tracks;
    private List<Album> albums;

    public Artist() {
    }

    public Artist(String id, String name) {
        super(id, name);
    }

    public Artist(String id, String name, List<Track> tracks, List<Album> albums) {
        super(id, name);
        this.tracks = tracks;
        this.albums = albums;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
