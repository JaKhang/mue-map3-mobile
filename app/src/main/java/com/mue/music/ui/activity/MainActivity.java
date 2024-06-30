package com.mue.music.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mue.music.R;
import com.mue.music.ui.fragment.HomeFragment;
import com.mue.music.ui.fragment.SearchFragment;
import com.mue.music.util.RenderFragmentUtil;

// Todo: Kiểm tra việc quay lại với nút back
public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ImageButton favUncheckBtn;
    private ImageButton favCheckBtn;
    private ImageButton playBtn;
    private ImageButton pauseBtn;
    private View musicPlayerBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set theme mặc định là theme tối
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        EdgeToEdge.enable(this);
        this.setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        favUncheckBtn = findViewById(R.id.fav_unchecked);
        favCheckBtn = findViewById(R.id.fav_checked);
        playBtn = findViewById(R.id.play);
        pauseBtn = findViewById(R.id.pause);
        musicPlayerBar = findViewById(R.id.music_player_bar);

        // Load HomeFragment ngay trong lần đầu tiên,
        // kiểm tra trạng thái trước đó từ param Bundle để xem fragment này đã load lần đầu tiên hay chưa
        if (savedInstanceState == null) {
            // Truyền activity hiện tại (Là con của 1 AppCompatActivity), instance của Fragment cần tạo
            // và id container của fragment đó trong activity tương ứng
            RenderFragmentUtil.loadFragment(this,new HomeFragment(), R.id.fragment_container);
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                RenderFragmentUtil.loadFragment(this, new HomeFragment(), R.id.fragment_container);
                return true;
            } else if (item.getItemId() == R.id.navigation_search) {
                RenderFragmentUtil.loadFragment(this, new SearchFragment(), R.id.fragment_container);
                return true;
            } else {
                return false;
            }
        });

        // Set trạng thái của nút yêu thích trên thanh phát nhạc và xử lý sự kiện
        favUncheckBtn.setOnClickListener(v -> {
            favUncheckBtn.setVisibility(View.GONE);
            favCheckBtn.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Add to You Liked", Toast.LENGTH_SHORT).show();
            // TODO: Xử lý sự kiện (thêm dữ liệu,...) khi nhấn
        });

        favCheckBtn.setOnClickListener(v -> {
            favUncheckBtn.setVisibility(View.VISIBLE);
            favCheckBtn.setVisibility(View.GONE);
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

        // Set sự kiện hiển thị Music Player Fragment khi bấm vào Music Player Bar
        musicPlayerBar.setOnClickListener(v -> {
            openMusicPlayer();
        });
    }

    private void openMusicPlayer() {
        Intent intent = new Intent(this, MusicPlayerActivity.class);
        startActivity(intent);
    }
}