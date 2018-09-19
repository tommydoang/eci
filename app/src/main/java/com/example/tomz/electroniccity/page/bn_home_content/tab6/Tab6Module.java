package com.example.tomz.electroniccity.page.bn_home_content.tab6;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.tab6.Tab6Adapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.page.bn_home_content.tab5.Tab5ViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class Tab6Module {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(Tab6ViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    Tab6ViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new Tab6ViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Tab6 tab6Frag){
        return new LinearLayoutManager(tab6Frag.getActivity());
    }

    @Provides
    Tab6Adapter provideAdapter(){ return new Tab6Adapter(new ArrayList<>()); }

}
