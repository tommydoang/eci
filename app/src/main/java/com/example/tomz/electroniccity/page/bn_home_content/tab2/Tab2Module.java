package com.example.tomz.electroniccity.page.bn_home_content.tab2;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.tab2.Tab2Adapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class Tab2Module {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(Tab2ViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    Tab2ViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new Tab2ViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Tab2 tab2Frag){
        return new LinearLayoutManager(tab2Frag.getActivity());
    }

    @Provides
    Tab2Adapter provideAdapter(){ return new Tab2Adapter(new ArrayList<>()); }


}
