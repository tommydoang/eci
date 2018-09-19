package com.example.tomz.electroniccity.page.splash;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {

    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager,
                                           SchedulerProvider schedulerProvider) {
        return new SplashViewModel(dataManager, schedulerProvider);
    }

}
