package com.example.tomz.electroniccity.page.bn_tab_home.account.address;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.AddressAdapter;
import com.example.tomz.electroniccity.adapter.tab1.Tab1Adapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class AddressModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(AddressViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    AddressViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new AddressViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Address address){
        return new LinearLayoutManager(address.getParent());
    }

    @Provides
    AddressAdapter provideAdapter(){ return new AddressAdapter(new ArrayList<>()); }

}
