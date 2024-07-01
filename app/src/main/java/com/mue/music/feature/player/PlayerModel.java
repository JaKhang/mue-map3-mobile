package com.mue.music.feature.player;

import com.mue.music.model.Track;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import lombok.Getter;

@Getter
public class PlayerModel {
    private int current;
    private Mode mode = Mode.NONE;
    private boolean isShuffle = true;
    private final List<Track> tracks = new ArrayList<>();
    private PlayerStatus status = PlayerStatus.PLAY;
    private final Queue<Integer> played = new LinkedList<>();
    private int randStart = 0;

    int next() {
        return current = getNextIndex();
    }

    int previous() {
        if (isShuffle)
            return current = randomTrack();
        if (current == 0)
            return current = tracks.size() - 1;
        return current = current - 1;
    }

    void setMode(Mode mode) {
        this.mode = mode;
    }

    void setStatus(PlayerStatus status) {
        this.status = status;
    }

    void setShuffle(boolean b) {
        this.isShuffle = b;

    }

    void setCurrent(int current){
        this.current = current;
    }

    public Track getCurrentTrack() {
        return tracks.get(current);
    }

    public int getNextIndex(){
        if (isShuffle)
            return randomTrack();
        if (current == tracks.size() - 1)
            return 0;
        return current + 1;
    }

    int randomTrack() {
        if (tracks.isEmpty())
            return 0;

        if (played.size() == tracks.size()) {
            int index = played.poll();
            played.offer(index);
            return index;
        }
        Random random = new Random();
        int index = random.nextInt(tracks.size());
        while (played.contains(index)) {
            index = random.nextInt(tracks.size());
        }
        if (played.isEmpty())
            randStart = index;
        played.offer(index);
        return index;

    }

    Queue<Integer> getPlayed() {
        return played;
    }

    public int getRandStart() {
        return randStart;
    }

    public boolean isEnd(){
        if (isShuffle)
            return played.size() == tracks.size();
        return current == tracks.size();
    }
}
