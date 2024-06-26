package com.mue.music;

import android.app.Application;
import android.util.Log;

import com.mue.music.config.ApiModule;
import com.mue.music.config.ApplicationComponents;
import com.mue.music.config.ContextModule;
import com.mue.music.config.DaggerApplicationComponents;

public class BaseApplication extends Application {
    private ApplicationComponents applicationComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("test", "onCreate");
        applicationComponents = DaggerApplicationComponents.builder()
                .contextModule(new ContextModule(this))
                .apiModule(new ApiModule())
                .build();

    }

    public ApplicationComponents getApplicationComponents() {
        return applicationComponents;
    }
}
