package com.example.tomz.electroniccity.page.bn_home_content.tab4;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.tab3.Tab3Adapter;
import com.example.tomz.electroniccity.adapter.tab4.Tab4Adapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.page.bn_home_content.tab3.Tab3;
import com.example.tomz.electroniccity.page.bn_home_content.tab3.Tab3ViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class Tab4Module {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(Tab4ViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    Tab4ViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new Tab4ViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Tab4 tab4Frag){
        return new LinearLayoutManager(tab4Frag.getActivity());
    }

    @Provides
    Tab4Adapter provideAdapter(){ return new Tab4Adapter(new ArrayList<>()); }

}
