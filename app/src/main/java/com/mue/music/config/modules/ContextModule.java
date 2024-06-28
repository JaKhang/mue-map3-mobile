package com.mue.music.config.modules;


import android.app.Application;

import com.mue.music.service.AuthenticationManger;
import com.mue.music.service.impl.DefaultAuthenticationManger;

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
    AuthenticationManger provideAuthenticationManger() {
        return new DefaultAuthenticationManger();
    }
}
