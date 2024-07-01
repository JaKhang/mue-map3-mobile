package com.mue.music.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.mue.music.BaseApplication;
import com.mue.music.R;
import com.mue.music.api.ApiHandler;
import com.mue.music.feature.player.PlayerService;
import com.mue.music.model.Genre;
import com.mue.music.model.Principal;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.repository.AuthenticationRepository;
import com.mue.music.repository.GenreRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

public class SplashScreenActivity extends AppCompatActivity {
    private static final long SPLASH_DISPLAY_LENGTH = 1000L;
    private final Set<String> task = new TreeSet<>();

    //Common data
    private List<Genre> genres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        super.onCreate(savedInstanceState);
        BaseApplication application = (BaseApplication) getApplication();
        application.getApplicationComponents().inject(this);
        setContentView(R.layout.activity_splash_screen);
        Log.e("JA", "Start Splash");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();

            }
        }, SPLASH_DISPLAY_LENGTH);
        Intent intent = new Intent(this, PlayerService.class);
        startService(intent);
    }

    private void loadData() {
        task.add("loginWithLocalToken");
        task.add("loadAllGenres");

        authenticationRepository.loginWithLocalToken(new ApiHandler<Principal>() {
            @Override
            public void onDone() {
                handleLoaded("loginWithLocalToken");
            }
        });

        genreRepository.findAll(new ApiHandler<List<Genre>>() {
            @Override
            public void onSuccess(ApiBody<List<Genre>> body) {
                genres = body.getData();
                ApiHandler.super.onSuccess(body);
            }

            @Override
            public void onDone() {
                handleLoaded("loadAllGenres");
            }
        });
        Log.e("JA", task.toString());


    }


    private void handleLoaded(String taskId) {
        Log.e("JA", task.toString());

        task.remove(taskId);
        if (!task.isEmpty()) {
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("genres", (Serializable) genres);
        Log.e("JA", "DONE");
        startActivity(intent);
        finish();
    }

    private AuthenticationRepository authenticationRepository;
    private GenreRepository genreRepository;


    @Inject
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Inject
    public void setAuthenticationRepository(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }


}