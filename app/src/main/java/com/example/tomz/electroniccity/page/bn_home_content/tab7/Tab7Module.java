package com.example.tomz.electroniccity.page.bn_home_content.tab7;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.tab6.Tab6Adapter;
import com.example.tomz.electroniccity.adapter.tab7.Tab7Adapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.page.bn_home_content.tab6.Tab6;
import com.example.tomz.electroniccity.page.bn_home_content.tab6.Tab6ViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class Tab7Module {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(Tab7ViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    Tab7ViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new Tab7ViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Tab7 tab7Frag){
        return new LinearLayoutManager(tab7Frag.getActivity());
    }

    @Provides
    Tab7Adapter provideAdapter(){ return new Tab7Adapter(new ArrayList<>()); }

}
