package com.mue.music.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mue.music.R;

public class DetailFragment extends Fragment {
    private Button followBtn;
    private ImageButton playBtn;
    private ImageButton pauseBtn;
    private ImageButton shufflePlayOffBtn;
    private ImageButton shufflePlayOnBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_generic_list, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        followBtn = view.findViewById(R.id.follow_button);
        playBtn = view.findViewById(R.id.play);
        pauseBtn = view.findViewById(R.id.pause);
        shufflePlayOffBtn = view.findViewById(R.id.shuffle_play_off);
        shufflePlayOnBtn = view.findViewById(R.id.shuffle_play_on);

        followBtn.setOnClickListener(v -> {
            if (followBtn.getText().equals("Follow")) {
                followBtn.setText("Followed");
            } else {
                followBtn.setText("Follow");
            }
        });

        playBtn.setOnClickListener(v -> {
            playBtn.setVisibility(View.GONE);
            pauseBtn.setVisibility(View.VISIBLE);
        });

        pauseBtn.setOnClickListener(v -> {
            playBtn.setVisibility(View.VISIBLE);
            pauseBtn.setVisibility(View.GONE);
        });

        shufflePlayOffBtn.setOnClickListener(v -> {
            shufflePlayOffBtn.setVisibility(View.GONE);
            shufflePlayOnBtn.setVisibility(View.VISIBLE);
        });

        shufflePlayOnBtn.setOnClickListener(v -> {
            shufflePlayOffBtn.setVisibility(View.VISIBLE);
            shufflePlayOnBtn.setVisibility(View.GONE);
        });
    }
}
