package com.mue.music.config;

import com.mue.music.MainActivity;
import com.mue.music.config.modules.ApiModule;
import com.mue.music.config.modules.ContextModule;
import com.mue.music.config.modules.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, ContextModule.class, ServiceModule.class})
public interface ApplicationComponents {
    void inject(MainActivity mainActivity);


}
