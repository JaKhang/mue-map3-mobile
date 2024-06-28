package com.mue.music.config.modules;

import android.app.Application;

import com.mue.music.service.AlbumService;
import com.mue.music.service.ApiService;
import com.mue.music.service.ArtistService;
import com.mue.music.service.AuthService;
import com.mue.music.service.AuthenticationManger;
import com.mue.music.service.GenreService;
import com.mue.music.service.PlayListService;
import com.mue.music.service.UserService;
import com.mue.music.service.impl.DefaultAlbumService;
import com.mue.music.service.impl.DefaultArtistService;
import com.mue.music.service.impl.DefaultAuthService;
import com.mue.music.service.impl.DefaultGenreService;
import com.mue.music.service.impl.DefaultPlayListService;
import com.mue.music.service.impl.DefaultUserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {
    @Singleton
    @Provides
    public ArtistService provideArtistService(ApiService apiService){
        return new DefaultArtistService(apiService);
    }


    @Singleton
    @Provides
    public AlbumService provideAlbumService(ApiService apiService){
        return new DefaultAlbumService(apiService);
    }


    @Singleton
    @Provides
    public PlayListService providePlayListService(ApiService apiService){
        return new DefaultPlayListService(apiService);
    }

    @Singleton
    @Provides
    public GenreService provideGenreService(ApiService apiService){
        return new DefaultGenreService(apiService);
    }


    @Singleton
    @Provides
    public AuthService provideAuthService(ApiService apiService, Application application, AuthenticationManger authenticationManger){
        return new DefaultAuthService(apiService,application, authenticationManger);
    }

    @Singleton
    @Provides
    public UserService userService (ApiService apiService){
        return new DefaultUserService(apiService);
    }




}
