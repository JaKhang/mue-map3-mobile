package com.mue.music.di;

import com.mue.music.MainActivity;
import com.mue.music.di.modules.ApiModule;
import com.mue.music.di.modules.ContextModule;
import com.mue.music.di.modules.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, ContextModule.class, ServiceModule.class})
public interface ApplicationComponents {
    void inject(MainActivity mainActivity);


}
