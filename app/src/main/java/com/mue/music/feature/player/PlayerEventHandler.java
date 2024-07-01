package com.mue.music.feature.player;

public interface PlayerEventHandler {

    default void onChangeMode(Mode mode){

    }

    default void onChangeTrack(int index){

    }

    default void onUpdateTime(int time){

    }


    default void onChangeStatus(PlayerStatus playerStatus){};


    default void onChangeTrackList(){};

    default void onStartPlay(){};

    default void onStopPlay(){};
}
