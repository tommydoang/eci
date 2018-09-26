package com.example.tomz.electroniccity.page.side_menu.value;

import android.arch.lifecycle.ViewModelProvider;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class ValueAddModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(ValueAddViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    ValueAddViewModel provideMainViewModel(DataManager dataManager,
                                           SchedulerProvider schedulerProvider){
        return new ValueAddViewModel(dataManager, schedulerProvider);
    }

}
