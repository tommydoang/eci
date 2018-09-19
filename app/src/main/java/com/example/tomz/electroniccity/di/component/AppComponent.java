package com.example.tomz.electroniccity.di.component;

import android.app.Application;

import com.example.tomz.electroniccity.MyApps;
import com.example.tomz.electroniccity.di.builder.ActivityBuilder;
import com.example.tomz.electroniccity.di.module.AppModule;
import com.example.tomz.electroniccity.di.module.ServiceModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        ServiceModule.class,
        ActivityBuilder.class,
        AndroidSupportInjectionModule.class})

public interface AppComponent {
    void inject(MyApps myApps);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
