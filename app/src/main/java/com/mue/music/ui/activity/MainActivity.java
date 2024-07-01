package com.mue.music.ui.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mue.music.BaseApplication;
import com.mue.music.R;
import com.mue.music.api.ApiHandler;
import com.mue.music.feature.auth.AuthenticationContext;
import com.mue.music.feature.player.PlayerEventHandler;
import com.mue.music.feature.player.PlayerReducer;
import com.mue.music.feature.player.PlayerStatus;
import com.mue.music.model.AlbumDetails;
import com.mue.music.model.Genre;
import com.mue.music.model.Track;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.ApiError;
import com.mue.music.repository.AlbumRepository;
import com.mue.music.repository.GenreRepository;
import com.mue.music.repository.UserRepository;
import com.mue.music.ui.fragment.HomeFragment;
import com.mue.music.ui.fragment.SearchFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements PlayerEventHandler {
    private BottomNavigationView bottomNavigationView;
    private ImageButton favUncheckBtn;
    private ImageButton favCheckBtn;
    private ImageButton playBtn;
    private ImageButton pauseBtn;
    private View musicPlayerBar;
    private TextView trackTile;
    private TextView artistName;
    private ImageView musicThumbnail;
    private ProgressBar progressBar;

    /*------------------
        Data
    --------------------*/
    private List<Genre> genres = new ArrayList<>();


    /*------------------
          PLayer
    --------------------*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inject dependency
        BaseApplication application = (BaseApplication) getApplication();
        application.getApplicationComponents().inject(this);
        playerReducer.addEventHandler(this);
        genres = (List<Genre>) getIntent().getSerializableExtra("genres");

//        EdgeToEdge.enable(this);
        this.setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        favUncheckBtn = findViewById(R.id.fav_unchecked);
        favCheckBtn = findViewById(R.id.fav_checked);
        playBtn = findViewById(R.id.play);
        pauseBtn = findViewById(R.id.pause);
        musicPlayerBar = findViewById(R.id.music_player_bar);
        trackTile = findViewById(R.id.music_title);
        artistName = findViewById(R.id.music_artist);
        musicThumbnail = findViewById(R.id.music_thumbnail);
        progressBar = findViewById(R.id.music_progress);
        // Load HomeFragment ngay trong lần đầu tiên,
        // kiểm tra trạng thái trước đó từ param Bundle để xem fragment này đã load lần đầu tiên hay chưa
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }
        Log.e("Log", "Start main");
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                loadFragment(new HomeFragment());
                return true;
            } else if (item.getItemId() == R.id.navigation_search) {

                loadFragment(new SearchFragment(genres));
                return true;
            } else {
                return false;
            }
        });

        // Set trạng thái của nút yêu thích trên thanh phát nhạc và xử lý sự kiện
        favUncheckBtn.setOnClickListener(v -> {
            Track track = playerReducer.getModel().getCurrentTrack();
            handleLike(track);
        });

        favCheckBtn.setOnClickListener(v -> {
            Track track = playerReducer.getModel().getCurrentTrack();
            handleUnlike(track);
        });

        // Set trạng thái cho nút play/pause
        playBtn.setOnClickListener(v -> {
            playerReducer.setStatus(PlayerStatus.PLAY);
        });

        pauseBtn.setOnClickListener(v -> {
            playerReducer.setStatus(PlayerStatus.PAUSE);
        });

        // Set sự kiện hiển thị Music Player Fragment khi bấm vào Music Player Bar
        musicPlayerBar.setOnClickListener(v -> {
//            openMusicPlayer();
        });

//        albumRepository.findById(UUID.fromString("55a82768-3d33-4e9a-a345-6d8df30ae07f"), new ApiHandler<AlbumDetails>() {
//            @Override
//            public void onSuccess(ApiBody<AlbumDetails> body) {
//                playerReducer.addTracks(body.getData().getTracks());
//                playerReducer.play();
//            }
//        });
        onStopPlay();
        onChangeStatus(playerReducer.getModel().getStatus());
        onChangeTrack(playerReducer.getModel().getCurrent());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.playerReducer.removeEventHandler(this);
    }


    /*------------------
       Player handler
    --------------------*/
    @Override
    public void onChangeTrack(int index) {
        if (index == -1)
            return;;
        Track track = playerReducer.getModel().getCurrentTrack();
        trackTile.setText(track.getName());
        artistName.setText(track.getArtistsName());
        Glide.with(this)
                .asBitmap()
                .load(track.getThumbnail())
                .placeholder(R.drawable.placeholder) // Optional: placeholder image
                .error(R.drawable.placeholder)
                .apply(new RequestOptions().transform(new RoundedCorners(15)).error(R.drawable.placeholder).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
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
                                int vibrantColor = makeColorDarker(palette.getVibrantColor(defaultColor), 0.3f);
                                setRoundedBackgroundColor(musicPlayerBar, vibrantColor);
                            }

                        });
                        return false;
                    }
                })
                .into(musicThumbnail);

        if(track.isLiked()){



            favUncheckBtn.setVisibility(View.GONE);
            favCheckBtn.setVisibility(View.VISIBLE);
        } else {
            favUncheckBtn.setVisibility(View.VISIBLE);
            favCheckBtn.setVisibility(View.GONE);
        }
    }
    private int makeColorDarker(int color, float factor) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.35f; // Reduce brightness
        hsv[1] *= 0.5f;
        return Color.HSVToColor(hsv);
    }


    @Override
    public void onChangeStatus(@NotNull PlayerStatus status) {
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
    public void onUpdateTime(int time) {
        progressBar.setProgress(time);
    }

    @Override
    public void onStartPlay() {
        musicPlayerBar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStopPlay() {
        musicPlayerBar.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }


    /*------------------
         Private
    --------------------*/
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

    private void handleLike(Track track){
        if (!authenticationContext.isAuthenticated()){
            handleNavigateToLogin();
        } else {
            userRepository.likeTracks(List.of(track.getId()), new ApiHandler<Void>() {
                @Override
                public void onSuccess(ApiBody<Void> body) {
                    favUncheckBtn.setVisibility(View.GONE);
                    favCheckBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Add to You Liked", Toast.LENGTH_SHORT).show();
                    track.setLiked(true);
                }

                @Override
                public void onFailure(ApiError apiError) {
                    Toast.makeText(MainActivity.this, "Add to You Liked failure", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private void setRoundedBackgroundColor(View layout, int color) {
        GradientDrawable background = (GradientDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.control_bar_round, null);
        assert background != null;
        background.setColor(color);
        layout.setBackground(background);
    }

    private void handleUnlike(Track track){
        if (!authenticationContext.isAuthenticated()){
            handleNavigateToLogin();
        } else {
            userRepository.unLikeTracks(List.of(track.getId()), new ApiHandler<Void>() {
                @Override
                public void onSuccess(ApiBody<Void> body) {
                    favUncheckBtn.setVisibility(View.VISIBLE);
                    favCheckBtn.setVisibility(View.GONE);
                    track.setLiked(false);
                    Toast.makeText(MainActivity.this, body.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(ApiError apiError) {
                    Toast.makeText(MainActivity.this, "Remove to You Liked failure", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private void handleNavigateToLogin(){
        Log.i("TEST", "To login");
    }


//    private void openMusicPlayer() {
//        Intent intent = new Intent(this, MusicPlayerActivity.class);
//        startActivity(intent);
//    }


    /*------------------
           DI
    --------------------*/
    private GenreRepository genreRepository;
    private PlayerReducer playerReducer;
    private AlbumRepository albumRepository;
    private UserRepository userRepository;
    private AuthenticationContext authenticationContext;


    @Inject
    public void setAuthenticationContext(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    @Inject
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Inject
    public void setAlbumRepository(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Inject
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Inject
    public void setPlaylistManger(PlayerReducer playerReducer) {
        this.playerReducer = playerReducer;
    }
}