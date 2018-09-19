package com.example.tomz.electroniccity.page.bn_tab_home.account;

import android.arch.lifecycle.ViewModelProvider;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class FragAccModule {

//    @Provides
//    ViewModelProvider.Factory fragAccViewModelProvider(FragAccViewModel fragAccViewModel){
//        return new ViewModelProviderFactory<>(fragAccViewModel);
//    }

    @Provides
    FragAccViewModel provideFragAccViewModel(DataManager dataManager,
                                             SchedulerProvider schedulerProvider){
        return new FragAccViewModel(dataManager, schedulerProvider);
    }

}
