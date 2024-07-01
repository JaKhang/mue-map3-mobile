package com.mue.music.di.modules;


import android.app.Application;

import com.mue.music.feature.auth.AuthenticationContext;
import com.mue.music.feature.auth.DefaultAuthenticationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Application application;


    public ContextModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    AuthenticationContext provideAuthenticationManger() {
        return new DefaultAuthenticationContext();
    }
}
