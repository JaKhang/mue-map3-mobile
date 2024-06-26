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
import com.mue.music.model.domain.InfiniteList;
import com.mue.music.service.ApiService;

import java.util.Objects;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;

    @Inject
    public void setApiService(ApiService apiService) {
        Log.i("setApiService", "Inject: " + (apiService != null));
        this.apiService = apiService;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Check", "onCreate");
        BaseApplication application = (BaseApplication) getApplication();
        application.getApplicationComponents().inject(this);

        apiService.findArtists(0, 10, null, "").enqueue(new Callback<ApiBody<InfiniteList<Artist>>>() {
            @Override
            public void onResponse(Call<ApiBody<InfiniteList<Artist>>> call, @NonNull Response<ApiBody<InfiniteList<Artist>>> response) {
                Log.i("tag", Objects.toString(response.body()));
                Log.e("error", "");

            }

            @Override
            public void onFailure(Call<ApiBody<InfiniteList<Artist>>> call, Throwable throwable) {
                Log.e("error",  "error" +    throwable.getMessage());

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