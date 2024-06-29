package com.mue.music.di.modules;

import android.app.Application;

import com.mue.music.api.Api;
import com.mue.music.repository.AlbumRepository;
import com.mue.music.repository.ArtistRepository;
import com.mue.music.repository.AuthenticationRepository;
import com.mue.music.feature.auth.AuthenticationManger;
import com.mue.music.repository.GenreRepository;
import com.mue.music.repository.PlayListRepository;
import com.mue.music.repository.UserRepository;
import com.mue.music.repository.impl.DefaultAlbumRepository;
import com.mue.music.repository.impl.DefaultArtistRepository;
import com.mue.music.repository.impl.DefaultAuthRepository;
import com.mue.music.repository.impl.DefaultGenreRepository;
import com.mue.music.repository.impl.DefaultPlayListRepository;
import com.mue.music.repository.impl.DefaultUserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {
    @Singleton
    @Provides
    public ArtistRepository provideArtistService(Api API){
        return new DefaultArtistRepository(API);
    }


    @Singleton
    @Provides
    public AlbumRepository provideAlbumService(Api API){
        return new DefaultAlbumRepository(API);
    }


    @Singleton
    @Provides
    public PlayListRepository providePlayListService(Api API){
        return new DefaultPlayListRepository(API);
    }

    @Singleton
    @Provides
    public GenreRepository provideGenreService(Api API){
        return new DefaultGenreRepository(API);
    }


    @Singleton
    @Provides
    public AuthenticationRepository provideAuthService(Api API, Application application, AuthenticationManger authenticationManger){
        return new DefaultAuthRepository(API,application, authenticationManger);
    }

    @Singleton
    @Provides
    public UserRepository userService (Api API){
        return new DefaultUserRepository(API);
    }




}
