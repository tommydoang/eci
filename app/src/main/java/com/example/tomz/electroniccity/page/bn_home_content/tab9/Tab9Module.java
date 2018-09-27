package com.example.tomz.electroniccity.page.bn_home_content.tab9;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.home_tab.tab9.Tab9Adapter;
import com.example.tomz.electroniccity.data.DataManager;

import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class Tab9Module {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(Tab9ViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    Tab9ViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new Tab9ViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Tab9 tab9Frag){
        return new LinearLayoutManager(tab9Frag.getActivity());
    }

    @Provides
    Tab9Adapter provideAdapter(){ return new Tab9Adapter(new ArrayList<>()); }

}
