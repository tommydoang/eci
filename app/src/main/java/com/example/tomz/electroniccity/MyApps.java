package com.example.tomz.electroniccity;

import android.app.Activity;
import android.app.Service;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.androidnetworking.AndroidNetworking;
import com.example.tomz.electroniccity.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;

public class MyApps extends MultiDexApplication implements HasActivityInjector, HasServiceInjector {

    @Inject DispatchingAndroidInjector<Activity> activityInjector;
    @Inject DispatchingAndroidInjector<Service> serviceInjector;
    private static MyApps mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initializeComponent();
        strictPermitNetwork();
    }

    private void strictPermitNetwork(){
        StrictMode.ThreadPolicy policy = new StrictMode
                .ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void initializeComponent() {
        DaggerAppComponent.builder().application(this).build().inject(this);
        AndroidNetworking.initialize(getApplicationContext());
    }

    public static synchronized MyApps getInstance() { return mInstance; }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceInjector;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
