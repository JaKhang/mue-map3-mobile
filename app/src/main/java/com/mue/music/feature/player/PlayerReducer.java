package com.mue.music.feature.player;

import com.mue.music.model.Track;

import java.util.List;

public interface PlayerReducer {

    void next();

    void previous();

    void addTracks(List<Track> tracks);

    void removeTracks(List<Track> tracks);

    void clear();

    void setMode(Mode mode);

    void play();

    void pause();

    void stop();

    void addEventHandler(PlayerEventHandler eventHandler);

    PlayerModel getModel();

    void setStatus(PlayerStatus playerStatus);

    void removeEventHandler(PlayerEventHandler handler);

    void endTrack();

    void updateTime(int percent);
}
