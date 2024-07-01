package com.mue.music.di;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mue.music.feature.auth.AuthenticationContext;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@RequiredArgsConstructor
public class AuthenticationInterceptor implements Interceptor {

    private final AuthenticationContext authenticationContext;

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        Log.i("info", authenticationContext.toString());
        Request.Builder requestBuilder = original.newBuilder();
        if (authenticationContext.getToken() != null)
            requestBuilder.header("Authorization", "Bearer " + authenticationContext.getToken()); // Adding Authorization header
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
