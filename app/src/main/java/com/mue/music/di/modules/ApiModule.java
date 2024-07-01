package com.mue.music.di.modules;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mue.music.api.Api;
import com.mue.music.di.AuthenticationInterceptor;
import com.mue.music.feature.auth.AuthenticationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    AuthenticationInterceptor provideAuthenticationInterceptor(AuthenticationContext authService){
        return new AuthenticationInterceptor(authService);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuildergsonBuilder = new GsonBuilder();;
        return gsonBuildergsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache, AuthenticationInterceptor authenticationInterceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        client.addInterceptor(interceptor);
        client.addInterceptor(authenticationInterceptor);
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://192.168.1.42:8080")
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    Api provideApiService(Retrofit retrofit){
        return retrofit.create(Api.class);
    }

}
