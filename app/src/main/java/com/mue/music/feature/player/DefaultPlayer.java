package com.mue.music.feature.player;

import android.util.Log;

import com.mue.music.model.Track;

import java.util.LinkedList;
import java.util.List;

public class DefaultPlayer implements PlayerReducer {
    private PlayerModel model;
    private final List<PlayerEventHandler> handlers = new LinkedList<>();
    private boolean isServiceStart = false;

    public DefaultPlayer() {
        this.model = new PlayerModel();
    }

    @Override
    public void next() {
        int next = model.next();
        this.handlers.forEach(eventHandler -> eventHandler.onChangeTrack(model.getCurrent()));
    }

    @Override
    public void previous() {
        this.handlers.forEach(eventHandler -> eventHandler.onChangeTrack(model.getCurrent()));
    }

    @Override
    public void addTracks(List<Track> tracks) {
        if (tracks.isEmpty())
            return;

        if (model.getTracks().isEmpty()) {
            model.getTracks().addAll(tracks);
            if (model.isShuffle()) {
                model.setCurrent(model.randomTrack());
            } else {
                model.setCurrent(0);
            }
            this.handlers.forEach(eventHandler -> {
                eventHandler.onStartPlay();
                eventHandler.onChangeTrack(model.getCurrent());
            });
        } else {
            model.getPlayed().clear();
            this.handlers.forEach(PlayerEventHandler::onChangeTrackList);
        }

    }

    @Override
    public void removeTracks(List<Track> tracks) {
        for (Track track : tracks) {

            int index = model.getTracks().indexOf(track);
            if (index == -1) return;
            int current = model.getCurrent();
            if (current == index) {
                if (model.isShuffle()) model.getPlayed().clear();
                next();
                continue;
            } else if (current > index) {
                current--;
                model.setCurrent(current);
            }


            if (model.getTracks().isEmpty()) {
                setStatus(PlayerStatus.PAUSE);
                this.handlers.forEach(PlayerEventHandler::onStopPlay);
                return;
            }
        }
    }

    @Override
    public void clear() {
        model.getTracks().clear();
        model.setCurrent(0);
        this.handlers.forEach(PlayerEventHandler::onStopPlay);

    }

    @Override
    public void setMode(Mode mode) {
        model.setMode(mode);
        this.handlers.forEach(eventHandler -> eventHandler.onChangeMode(model.getMode()));
    }

    @Override
    public void play() {
        this.handlers.forEach(eventHandler -> eventHandler.onChangeStatus(PlayerStatus.PLAY));
    }

    @Override
    public void pause() {
        this.handlers.forEach(eventHandler -> eventHandler.onChangeStatus(PlayerStatus.PAUSE));
        ;
    }

    @Override
    public void stop() {
        this.handlers.forEach(PlayerEventHandler::onStopPlay);
    }

    @Override
    public void addEventHandler(PlayerEventHandler eventHandler) {
        handlers.add(eventHandler);
    }

    @Override
    public PlayerModel getModel() {
        return model;
    }

    @Override
    public void setStatus(PlayerStatus playerStatus) {
        model.setStatus(playerStatus);
        handlers.forEach(eventHandler -> eventHandler.onChangeStatus(playerStatus));
    }

    @Override
    public void removeEventHandler(PlayerEventHandler handler) {
        this.handlers.remove(handler);
    }

    @Override
    public void endTrack() {
        Log.i("TEST", model.getPlayed().toString());
        if (model.getMode() == Mode.REPEAT) {
            return;
        }
        if (model.getMode() == Mode.NONE && getModel().isEnd()) {
            setStatus(PlayerStatus.PAUSE);
        }
        next();
    }

    @Override
    public void updateTime(int percent) {
        handlers.forEach(eventHandler -> eventHandler.onUpdateTime(percent));
    }

    @Override
    public void setShuffle(boolean b) {
        if (!b) model.getPlayed().clear();
        model.setShuffle(b);
        handlers.forEach(eventHandler -> eventHandler.onShuffle(b));
    }


}
