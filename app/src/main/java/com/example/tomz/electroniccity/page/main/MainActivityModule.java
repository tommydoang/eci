package com.example.tomz.electroniccity.page.main;

import android.arch.lifecycle.ViewModelProvider;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel){
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new MainViewModel(dataManager, schedulerProvider);
    }


//    @Provides
//    FragmentManager provideFragmentManager(MainActivity mainActivity) {
//        Log.d("fragManager tes1", "CALLED!!");
//        return mainActivity.getSupportFragmentManager();
//    }

//    @Provides
//    ViewPagerTabAdapter providePagerAdapter(FragmentManager fm){
//        return new ViewPagerTabAdapter(fm);
//    }

}
