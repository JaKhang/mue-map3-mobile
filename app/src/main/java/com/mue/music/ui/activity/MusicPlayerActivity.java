package com.mue.music.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.mue.music.BaseApplication;
import com.mue.music.R;
import com.mue.music.feature.player.PlayerReducer;

import javax.inject.Inject;

public class MusicPlayerActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        this.setContentView(R.layout.activity_music_player);
        BaseApplication application = (BaseApplication) getApplication();
        application.getApplicationComponents().inject(this);

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

        // Set sự kiện cho các nút đã tạo
        setEventForBtn();

        // Đóng Music Player
        findViewById(R.id.expand_button).setOnClickListener(v -> {
            finish();
            overridePendingTransition(0, R.anim.slide_down);
        });
    }

    private void setEventForBtn() {
        // Set trạng thái của nút yêu thích trên thanh phát nhạc và xử lý sự kiện
        favUncheckedBtn.setOnClickListener(v -> {
            favUncheckedBtn.setVisibility(View.GONE);
            favCheckedBtn.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Add to You Liked", Toast.LENGTH_SHORT).show();
            // TODO: Xử lý sự kiện (thêm dữ liệu,...) khi nhấn
        });

        favCheckedBtn.setOnClickListener(v -> {
            favUncheckedBtn.setVisibility(View.VISIBLE);
            favCheckedBtn.setVisibility(View.GONE);
            Toast.makeText(this, "Remove from You Liked", Toast.LENGTH_SHORT).show();
            // TODO: Xử lý sự kiện (xóa dữ liệu,...) khi nhấn
        });

        shuffleOffBtn.setOnClickListener(v -> {
            shuffleOnBtn.setVisibility(View.VISIBLE);
            shuffleOffBtn.setVisibility(View.GONE);
        });

        shuffleOnBtn.setOnClickListener(v -> {
            shuffleOffBtn.setVisibility(View.VISIBLE);
            shuffleOnBtn.setVisibility(View.GONE);
        });

        // Set trạng thái cho nút play/pause
        playBtn.setOnClickListener(v -> {
            playBtn.setVisibility(View.GONE);
            pauseBtn.setVisibility(View.VISIBLE);
        });

        pauseBtn.setOnClickListener(v -> {
            playBtn.setVisibility(View.VISIBLE);
            pauseBtn.setVisibility(View.GONE);
        });

        repeatOffBtn.setOnClickListener(v -> {
            repeatAllBtn.setVisibility(View.VISIBLE);
            repeatOffBtn.setVisibility(View.GONE);
        });

        repeatAllBtn.setOnClickListener(v -> {
            repeatOneBtn.setVisibility(View.VISIBLE);
            repeatAllBtn.setVisibility(View.GONE);
        });

        repeatOneBtn.setOnClickListener(v -> {
            repeatOffBtn.setVisibility(View.VISIBLE);
            repeatOneBtn.setVisibility(View.GONE);
        });
    }


    private PlayerReducer playerReducer;

    @Inject
    public void setPlayerReducer(PlayerReducer playerReducer) {
        this.playerReducer = playerReducer;
    }
}
