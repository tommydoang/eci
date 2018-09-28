package com.example.tomz.electroniccity.page.side_menu.policy;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.side_menu.policy.PolicyAdapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class PolicyModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(PolicyViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    PolicyViewModel provideViewModel(DataManager dataManager,
                                     SchedulerProvider schedulerProvider){
        return new PolicyViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Policy policy){
        return new LinearLayoutManager(policy.getParent());
    }

    @Provides
    PolicyAdapter provideAdapter(){ return new PolicyAdapter(new ArrayList<>()); }


}
