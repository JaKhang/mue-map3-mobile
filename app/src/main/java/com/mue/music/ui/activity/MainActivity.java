package com.mue.music.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mue.music.R;
import com.mue.music.ui.fragment.HomeFragment;
import com.mue.music.ui.fragment.SearchFragment;

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
            loadFragment(new HomeFragment());
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                loadFragment(new HomeFragment());
                return true;
            } else if (item.getItemId() == R.id.navigation_search) {
                loadFragment(new SearchFragment());
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
//            openMusicPlayer();
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (currentFragment != null && currentFragment.getClass().equals(fragment.getClass())) {
            // Hàm if kiểm tra xem fragment class muốn load có phải là fragment class hiện tại hay không,
            // nếu có thì không load fragment class lên (Tối ưu việc load fragment)
            return;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

//    private void openMusicPlayer() {
//        Intent intent = new Intent(this, MusicPlayerActivity.class);
//        startActivity(intent);
//    }
}