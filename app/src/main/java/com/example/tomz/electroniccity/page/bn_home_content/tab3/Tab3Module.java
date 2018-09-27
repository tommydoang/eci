package com.example.tomz.electroniccity.page.bn_home_content.tab3;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.home_tab.tab3.Tab3Adapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class Tab3Module {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(Tab3ViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    Tab3ViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new Tab3ViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Tab3 tab3Frag){
        return new LinearLayoutManager(tab3Frag.getActivity());
    }

    @Provides
    Tab3Adapter provideAdapter(){ return new Tab3Adapter(new ArrayList<>()); }

}
