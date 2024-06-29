package com.mue.music;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mue.music.repository.AuthenticationRepository;
import com.mue.music.repository.UserRepository;
import com.mue.music.model.Principal;
import com.mue.music.model.domain.ApiBody;
import com.mue.music.model.domain.ApiError;
import com.mue.music.model.request.LoginRequest;
import com.mue.music.api.ApiHandler;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private AuthenticationRepository authenticationRepository;
    private UserRepository userRepository;

    @Inject
    public void setAuthService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }


    @Inject
    public void setUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Check", "onCreate");
        BaseApplication application = (BaseApplication) getApplication();
        application.getApplicationComponents().inject(this);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("ltndiep0803@gmail.com");
        loginRequest.setPassword("12345");
        authenticationRepository.login(loginRequest, new ApiHandler<Principal>() {
            @Override
            public void onSuccess(ApiBody<Principal> body) {
                Log.i("success", body.getData().toString());
                testLike();
            }

            @Override
            public void onFailure(ApiError apiError) {
                if (apiError != null)
                    Log.i("error", apiError.getMessage());
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

    private void testLike() {
        userRepository.likeTracks(List.of(UUID.fromString("02acbaaf-815d-4aa3-9c2c-62fb72313f46")),new ApiHandler<Void>() {
            @Override
            public void onSuccess(ApiBody<Void> body) {
                Log.i("Message", body.getMessage());
            }

            @Override
            public void onFailure(ApiError apiError) {
                if (apiError != null)
                    Log.i("Error", apiError.getMessage());
            }
        });
    }
}