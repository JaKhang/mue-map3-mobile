package com.mue.music.ui.activity;

import static com.mue.music.util.CommonUtils.calculatePartFormPercent;
import static com.mue.music.util.CommonUtils.convertTimeString;
import static com.mue.music.util.CommonUtils.makeColorDarker;

import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.mue.music.BaseApplication;
import com.mue.music.R;
import com.mue.music.api.ApiHandler;
import com.mue.music.feature.auth.AuthenticationContext;
import com.mue.music.feature.player.Mode;
import com.mue.music.feature.player.PlayerEventHandler;
import com.mue.music.feature.player.PlayerModel;
import com.mue.music.feature.player.PlayerReducer;
import com.mue.music.feature.player.PlayerStatus;
import com.mue.music.model.Track;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.ApiError;
import com.mue.music.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

public class MusicPlayerActivity extends AppCompatActivity implements PlayerEventHandler {
    private ImageButton favCheckedBtn;
    private ImageButton favUncheckedBtn;
    private ImageButton shuffleOffBtn;
    private ImageButton shuffleOnBtn;
    private ImageButton prevBtn;
    private ImageButton playBtn;
    private ImageButton pauseBtn;
    private ImageButton nextBtn;
    private ImageButton repeatOffBtn;
    private ImageButton repeatAllBtn;
    private ImageButton repeatOneBtn;
    private ImageView thumbnail;
    private TextView title;
    private TextView artist;
    private TextView subtitle;
    private TextView currentTime;
    private TextView durationTime;
    private SeekBar seekBar;
    private View container;
    private boolean isChangingDuration = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        this.setContentView(R.layout.activity_music_player);
        BaseApplication application = (BaseApplication) getApplication();
        application.getApplicationComponents().inject(this);
        playerReducer.addEventHandler(this);

        favCheckedBtn = findViewById(R.id.fav_checked);
        favUncheckedBtn = findViewById(R.id.fav_unchecked);
        shuffleOffBtn = findViewById(R.id.btn_shuffle_off);
        shuffleOnBtn = findViewById(R.id.btn_shuffle_on);
        prevBtn = findViewById(R.id.btn_previous);
        playBtn = findViewById(R.id.btn_play);
        pauseBtn = findViewById(R.id.btn_pause);
        nextBtn = findViewById(R.id.btn_next);
        repeatOffBtn = findViewById(R.id.btn_repeat_off);
        repeatAllBtn = findViewById(R.id.btn_repeat_all);
        repeatOneBtn = findViewById(R.id.btn_repeat_one);
        seekBar = findViewById(R.id.seekBar);
        thumbnail = findViewById(R.id.music_album_cover);
        subtitle = findViewById(R.id.music_artist_under);
        title = findViewById(R.id.music_title);
        artist = findViewById(R.id.music_artist);
        container = findViewById(R.id.container);
        currentTime = findViewById(R.id.startTime);
        durationTime = findViewById(R.id.endTime);


        // Set sự kiện cho các nút đã tạo
        setEventForBtn();

        // Đóng Music Player
        findViewById(R.id.expand_button).setOnClickListener(v -> {
            finish();
            overridePendingTransition(0, R.anim.slide_down);
        });

        updateView();
    }

    private void updateView() {
        PlayerModel model = playerReducer.getModel();
        onChangeStatus(model.getStatus());
        onChangeTrack(model.getCurrent());
        onShuffle(model.isShuffle());
    }

    private void setEventForBtn() {
        nextBtn.setOnClickListener(v -> playerReducer.next());
        prevBtn.setOnClickListener(v -> playerReducer.previous());
        // Set trạng thái của nút yêu thích trên thanh phát nhạc và xử lý sự kiện
        favUncheckedBtn.setOnClickListener(v -> {
            handleLike(playerReducer.getModel().getCurrentTrack());

        });

        favCheckedBtn.setOnClickListener(v -> {
            handleUnlike(playerReducer.getModel().getCurrentTrack());
        });

        shuffleOffBtn.setOnClickListener(v -> playerReducer.setShuffle(true));

        shuffleOnBtn.setOnClickListener(v -> playerReducer.setShuffle(false));

        // Set trạng thái cho nút play/pause
        playBtn.setOnClickListener(v -> playerReducer.setStatus(PlayerStatus.PLAY));

        pauseBtn.setOnClickListener(v -> playerReducer.setStatus(PlayerStatus.PAUSE));

        repeatOffBtn.setOnClickListener(v -> playerReducer.setMode(Mode.LOOP));

        repeatAllBtn.setOnClickListener(v -> playerReducer.setMode(Mode.REPEAT));

        repeatOneBtn.setOnClickListener(v -> playerReducer.setMode(Mode.NONE));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    onUpdateTime(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isChangingDuration = true;
                playerReducer.setStatus(PlayerStatus.PAUSE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int percent = seekBar.getProgress();
                playerReducer.updateTime(percent);
                playerReducer.setStatus(PlayerStatus.PLAY);
                isChangingDuration = false;

            }
        });
    }

    private void handleLike(Track track) {
        if (!authenticationContext.isAuthenticated()) {
            handleNavigateToLogin();
        } else {
            userRepository.likeTracks(List.of(track.getId()), new ApiHandler<Void>() {
                @Override
                public void onSuccess(ApiBody<Void> body) {
                    favUncheckedBtn.setVisibility(View.GONE);
                    favCheckedBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(MusicPlayerActivity.this, "Add to You Liked", Toast.LENGTH_SHORT).show();
                    track.setLiked(true);
                }

                @Override
                public void onFailure(ApiError apiError) {
                    Toast.makeText(MusicPlayerActivity.this, "Add to You Liked failure", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private void handleNavigateToLogin() {
    }

    private void handleUnlike(Track track) {
        if (!authenticationContext.isAuthenticated()) {
            handleNavigateToLogin();
        } else {
            userRepository.unLikeTracks(List.of(track.getId()), new ApiHandler<Void>() {
                @Override
                public void onSuccess(ApiBody<Void> body) {
                    favUncheckedBtn.setVisibility(View.VISIBLE);
                    favCheckedBtn.setVisibility(View.GONE);
                    track.setLiked(false);
                    Toast.makeText(MusicPlayerActivity.this, body.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(ApiError apiError) {
                    Toast.makeText(MusicPlayerActivity.this, "Remove to You Liked failure", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        playerReducer.removeEventHandler(this);
        super.onDestroy();
    }


    /*------------------
         Event
    --------------------*/

    @Override
    public void onUpdateTime(int percent) {
        if (!isChangingDuration)
            seekBar.setProgress(percent);
        int duration = playerReducer.getModel().getDuration();
        Log.i("TEST", "" + duration);
        currentTime.setText(convertTimeString(calculatePartFormPercent(percent, duration)));
        durationTime.setText(convertTimeString(duration));
    }

    @Override
    public void onChangeStatus(PlayerStatus status) {
        switch (status) {
            case PLAY:
                playBtn.setVisibility(View.GONE);
                pauseBtn.setVisibility(View.VISIBLE);
                break;
            case PAUSE:
                playBtn.setVisibility(View.VISIBLE);
                pauseBtn.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onShuffle(boolean b) {
        if (!b) {
            shuffleOnBtn.setVisibility(View.GONE);
            shuffleOffBtn.setVisibility(View.VISIBLE);
        } else {
            shuffleOnBtn.setVisibility(View.VISIBLE);
            shuffleOffBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onChangeMode(Mode mode) {
        switch (mode) {
            case NONE:
                repeatOffBtn.setVisibility(View.VISIBLE);
                repeatOneBtn.setVisibility(View.GONE);
                break;
            case LOOP:
                repeatAllBtn.setVisibility(View.VISIBLE);
                repeatOffBtn.setVisibility(View.GONE);
                break;
            case REPEAT:
                repeatOneBtn.setVisibility(View.VISIBLE);
                repeatAllBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onChangeTrack(int index) {
        if (index == -1) {
            return;
        }
        Track track = playerReducer.getModel().getCurrentTrack();

        Glide.with(this)
                .asBitmap()
                .load(track.getImage().getUrl())
                .placeholder(R.drawable.cycle_loader) // Optional: placeholder image
                .error(R.drawable.cycle_loader)
                .listener(new RequestListener<Bitmap>() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Bitmap resource, @NonNull Object model, Target<Bitmap> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        // Extract colors using Palette
                        Palette.from(resource).generate(palette -> {
                            if (palette != null) {
//                                int color = makeColorDarker(palette.getLightVibrantColor(1), 0.3f);
//
//                                musicPlayerBar.setBackgroundColor(color);
                                int defaultColor = 0xFF333333;
                                int vibrantColor = makeColorDarker(palette.getVibrantColor(palette.getDominantColor(defaultColor)));
                                int darkerColor = makeColorDarker(palette.getDarkVibrantColor(palette.getDominantColor(defaultColor)));
                                GradientDrawable gradientDrawable = new GradientDrawable(
                                        GradientDrawable.Orientation.TOP_BOTTOM, // Gradient direction
                                        new int[]{vibrantColor, darkerColor} // Gradient colors
                                );

                                // Optionally, set the corner radius or other properties
                                gradientDrawable.setCornerRadius(0f);

                                // Set the GradientDrawable as the background
                                container.setBackground(gradientDrawable);
                            }

                        });
                        return false;
                    }
                })
                .into(thumbnail);
        title.setText(track.getName());
        artist.setText(track.getAlbum().getName());
        subtitle.setText(track.getArtistsName());
        if (track.isLiked()) {
            favUncheckedBtn.setVisibility(View.GONE);
            favCheckedBtn.setVisibility(View.VISIBLE);
        } else {
            favUncheckedBtn.setVisibility(View.VISIBLE);
            favCheckedBtn.setVisibility(View.GONE);
        }
    }

    /*------------------
        DI
    --------------------*/
    private AuthenticationContext authenticationContext;
    private PlayerReducer playerReducer;
    private UserRepository userRepository;


    @Inject
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Inject
    public void setPlayerReducer(PlayerReducer playerReducer) {
        this.playerReducer = playerReducer;
    }

    @Inject
    public void setAuthenticationContext(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }
}
