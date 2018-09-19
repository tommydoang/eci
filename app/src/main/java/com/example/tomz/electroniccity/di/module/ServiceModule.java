package com.example.tomz.electroniccity.di.module;

import com.example.tomz.electroniccity.utils.services.date.DateService;
import com.example.tomz.electroniccity.utils.services.location.LocationService;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServiceModule {

    @ContributesAndroidInjector
    abstract LocationService provideLocationService();

    @ContributesAndroidInjector
    abstract DateService provideDateService();

}
