package com.example.tomz.electroniccity.page.bn_home_content.tab8;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.tab7.Tab7Adapter;
import com.example.tomz.electroniccity.adapter.tab8.Tab8Adapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.page.bn_home_content.tab7.Tab7;
import com.example.tomz.electroniccity.page.bn_home_content.tab7.Tab7ViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class Tab8Module {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(Tab8ViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    Tab8ViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new Tab8ViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Tab8 tab8Frag){
        return new LinearLayoutManager(tab8Frag.getActivity());
    }

    @Provides
    Tab8Adapter provideAdapter(){ return new Tab8Adapter(new ArrayList<>()); }


}
