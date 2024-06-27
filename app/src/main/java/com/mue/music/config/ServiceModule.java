package com.mue.music.config;

import com.mue.music.service.AlbumService;
import com.mue.music.service.ApiService;
import com.mue.music.service.ArtistService;
import com.mue.music.service.AuthService;
import com.mue.music.service.impl.DefaultArtistService;

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
    public AuthService provideAuthService(ApiService apiService){
        return new DefaultAuthService(apiService);
    }


    @Singleton
    @Provides
    public AlbumService provideAlbumService(ApiService apiService){
        return new DefaultAlbumService(apiService);
    }

    @Singleton
    @Provides
    public AuthService provideAuthService(ApiService apiService){
        return new DefaultAuthService(apiService);
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







}
