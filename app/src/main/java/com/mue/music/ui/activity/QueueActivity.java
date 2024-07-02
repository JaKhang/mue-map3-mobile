package com.mue.music.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.mue.music.R;

public class QueueActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_queue);

        // Đóng Music Player
        findViewById(R.id.expand_button).setOnClickListener(v -> {
            finish();
            overridePendingTransition(0, R.anim.slide_down);
        });

        // TODO: Render bằng QueueAdapter
    }
}
