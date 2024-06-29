package com.mue.music;

import android.app.Application;
import android.util.Log;

import com.mue.music.di.modules.ApiModule;
import com.mue.music.di.ApplicationComponents;
import com.mue.music.di.modules.ContextModule;
import com.mue.music.config.DaggerApplicationComponents;
import com.mue.music.di.modules.ServiceModule;

public class BaseApplication extends Application {
    private ApplicationComponents applicationComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Check", "Base Application create");
        applicationComponents = DaggerApplicationComponents.builder()
                .contextModule(new ContextModule(this))
                .apiModule(new ApiModule())
                .serviceModule(new ServiceModule())
                .build();

    }

    public ApplicationComponents getApplicationComponents() {
        return applicationComponents;
    }
}
