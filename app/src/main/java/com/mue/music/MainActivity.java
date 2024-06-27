package com.mue.music;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mue.music.config.ApplicationComponents;
import com.mue.music.model.Artist;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.ApiError;
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.model.domain.PageRequest;
import com.mue.music.service.ApiHandler;
import com.mue.music.service.ApiService;
import com.mue.music.service.ArtistService;

import java.util.Objects;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArtistService artistService;

    @Inject
    public void setApiService(ArtistService artistService) {
        Log.i("setApiService", "Inject: " + (artistService != null));
        this.artistService = artistService;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Check", "onCreate");
        BaseApplication application = (BaseApplication) getApplication();
        application.getApplicationComponents().inject(this);

        artistService.findAll(PageRequest.of(0, 10),
                new ApiHandler<InfiniteList<Artist>>() {
                    @Override
                    public void onSuccess(ApiBody<InfiniteList<Artist>> body) {

                    }

                    @Override
                    public void onFailure(ApiError apiError) {

                    }
                });

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}