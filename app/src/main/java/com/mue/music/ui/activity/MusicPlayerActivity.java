package com.mue.music.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.mue.music.R;

public class MusicPlayerActivity extends AppCompatActivity {
    private ImageButton favCheckedBtn;
    private ImageButton favUncheckedBtn;
    private ImageButton playBtn;
    private ImageButton pauseBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        EdgeToEdge.enable(this);
        this.setContentView(R.layout.activity_music_player);

        favCheckedBtn = findViewById(R.id.fav_checked);
        favUncheckedBtn = findViewById(R.id.fav_unchecked);
        playBtn = findViewById(R.id.btn_play);
        pauseBtn = findViewById(R.id.btn_pause);

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

        // Set trạng thái cho nút play/pause
        playBtn.setOnClickListener(v -> {
            playBtn.setVisibility(View.GONE);
            pauseBtn.setVisibility(View.VISIBLE);
        });

        pauseBtn.setOnClickListener(v -> {
            playBtn.setVisibility(View.VISIBLE);
            pauseBtn.setVisibility(View.GONE);
        });

        // Đóng Music Player
        findViewById(R.id.expand_button).setOnClickListener(v -> {
            finish();
            overridePendingTransition(0, R.anim.slide_down);
        });
    }
}
