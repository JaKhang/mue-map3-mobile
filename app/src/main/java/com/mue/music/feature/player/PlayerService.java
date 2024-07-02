package com.mue.music.feature.player;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;

import com.mue.music.BaseApplication;
import com.mue.music.api.ApiHandler;
import com.mue.music.model.Track;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.ApiError;
import com.mue.music.model.enums.Bitrate;
import com.mue.music.repository.TrackRepository;

import java.util.Map;

import javax.inject.Inject;


public class PlayerService extends Service implements PlayerEventHandler {
    public static final String CHANNEL_ID = "PLAYER_CHANNEL";
    private ExoPlayer player;
    private PlayerStatus prev = PlayerStatus.PLAY;
    private final Handler updateTimeCounter = new Handler();
    private long delay = 500;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TEST", "Service create");
        BaseApplication application = (BaseApplication) getApplication();
        application.getApplicationComponents().inject(this);
        player = new ExoPlayer.Builder(this).build();
        player.addListener(new Player.Listener() {
            @Override
            public void onIsPlayingChanged(boolean isPlaying) {
                if (player.isPlaying()) {
                    updateTimeCounter.postDelayed(PlayerService.this::getCurrentPlayerPosition, player.getDuration()/100);
                }
            }

            public void onPlaybackStateChanged(int playbackState) {
                if (playbackState == Player.STATE_ENDED) {
                    playerReducer.endTrack();
                } else if (playbackState == Player.STATE_READY){
                    playerReducer.getModel().setDuration((int) player.getDuration()/1000);

                }
            }

        });

    }

    private void getCurrentPlayerPosition() {
        playerReducer.updateTime((int) ((player.getCurrentPosition() / (double)player.getDuration()) * 100));

        if (player.isPlaying()) {
            updateTimeCounter.postDelayed(this::getCurrentPlayerPosition, player.getDuration()/100);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /*------------------
         Override
    --------------------*/

    @Override
    public void onChangeStatus(PlayerStatus playerStatus) {
        Log.i("TEST", "Service change status");
        switch (playerStatus) {

            case PAUSE:
                player.pause();
                break;
            case PLAY:
                player.play();
                break;
        }
    }

    @Override
    public void onStopPlay() {
        player.stop();
    }

    @Override
    public void onChangeMode(Mode mode) {
        if (mode ==  Mode.REPEAT){
            player.setRepeatMode(Player.REPEAT_MODE_ONE);
        } else if (player.getRepeatMode() == Player.REPEAT_MODE_ONE){
            player.setRepeatMode(Player.REPEAT_MODE_OFF);
        }
    }

    @Override
    public void onChangeTrack(int index) {
        if (index == -1)
            playerReducer.setStatus(PlayerStatus.PAUSE);
        PlayerModel model = playerReducer.getModel();
        Track track = model.getCurrentTrack();
        playTrack(track);
    }

    /*------------------
           private
    --------------------*/
    private String getStreamingURL(Map<Bitrate, String> data) {
        return data.get(Bitrate.HIGH);
    }



    private void playTrack(Track track) {
        prev = playerReducer.getModel().getStatus();
        playerReducer.setStatus(PlayerStatus.LOADING);
        player.stop();
        trackRepository.getStreamingUrl(track.getId(), new ApiHandler<Map<Bitrate, String>>() {
            @Override
            public void onSuccess(ApiBody<Map<Bitrate, String>> body) {
                String url = getStreamingURL(body.getData());
                player.removeMediaItem(0);
                player.setMediaItem(MediaItem.fromUri(url));
                player.prepare();
                playerReducer.setStatus(prev);
                delay = player.getDuration() / 100;
                Log.i("TEST", "Play track " + player.getDuration());
            }

            @Override
            public void onFailure(ApiError apiError) {
                playerReducer.setStatus(PlayerStatus.PAUSE);
            }
        });
    }

    @Override
    public void onUpdateTime(int percent) {
        if(playerReducer.getModel().getStatus() == PlayerStatus.PAUSE){
            long time = (player.getDuration() * percent) /100;
            player.seekTo(time);
        }

    }

    /*------------------
         DI
    --------------------*/

    private TrackRepository trackRepository;
    private PlayerReducer playerReducer;

    @Inject
    public void setTrackRepository(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Inject
    public void setPlaylistManger(PlayerReducer playerReducer) {
        this.playerReducer = playerReducer;
        playerReducer.addEventHandler(this);
    }


}
