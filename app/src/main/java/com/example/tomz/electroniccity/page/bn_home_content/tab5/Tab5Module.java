package com.example.tomz.electroniccity.page.bn_home_content.tab5;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.home_tab.tab5.Tab5Adapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class Tab5Module {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(Tab5ViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    Tab5ViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new Tab5ViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Tab5 tab5Frag){
        return new LinearLayoutManager(tab5Frag.getActivity());
    }

    @Provides
    Tab5Adapter provideAdapter(){ return new Tab5Adapter(new ArrayList<>()); }

}
