package com.example.tomz.electroniccity.page.bn_home_content.tab1;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.home_tab.tab1.Tab1Adapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab1.Tab1DealAdapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class Tab1Module {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(Tab1ViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    Tab1ViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new Tab1ViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Tab1 tab1Frag){
        return new LinearLayoutManager(tab1Frag.getActivity());
    }

    @Provides
    Tab1Adapter provideAdapter(){ return new Tab1Adapter(new ArrayList<>()); }

    @Provides
    Tab1DealAdapter provideDealAdapter() { return new Tab1DealAdapter(new ArrayList<>()); }


}
