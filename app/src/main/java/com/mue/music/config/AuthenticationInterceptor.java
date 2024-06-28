package com.mue.music.config;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mue.music.service.AuthService;
import com.mue.music.service.AuthenticationManger;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@RequiredArgsConstructor
public class AuthenticationInterceptor implements Interceptor {

    private final AuthenticationManger authenticationManger;

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        Log.i("info", authenticationManger.toString());
        Request.Builder requestBuilder = original.newBuilder();
        if (authenticationManger.getToken() != null)
            requestBuilder.header("Authorization", "Bearer " + authenticationManger.getToken()); // Adding Authorization header
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
