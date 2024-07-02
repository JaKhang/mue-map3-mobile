package com.mue.music.di;

import com.mue.music.di.modules.ApiModule;
import com.mue.music.di.modules.ContextModule;
import com.mue.music.di.modules.ServiceModule;
import com.mue.music.feature.player.PlayerService;
import com.mue.music.ui.activity.MainActivity;
import com.mue.music.ui.activity.SplashScreenActivity;
import com.mue.music.ui.fragment.HomeFragment;
import com.mue.music.ui.fragment.SearchFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, ContextModule.class, ServiceModule.class})
public interface ApplicationComponents {
    void inject(MainActivity mainActivity);

    void inject(SplashScreenActivity splashScreenActivity);

    void inject(PlayerService playerService);

    void inject(HomeFragment homeFragment);

    void inject(SearchFragment searchFragment);
}
