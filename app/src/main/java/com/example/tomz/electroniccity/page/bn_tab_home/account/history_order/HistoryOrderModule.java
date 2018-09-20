package com.example.tomz.electroniccity.page.bn_tab_home.account.history_order;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.HistoryOrderAdapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class HistoryOrderModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(HistoryOrderViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }


    @Provides
    HistoryOrderViewModel provideHistoryOrderViewModel(DataManager dataManager,
                                                       SchedulerProvider schedulerProvider){
        return new HistoryOrderViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(HistoryOrder historyOrder){
        return new LinearLayoutManager(historyOrder.getParent());
    }

    @Provides
    HistoryOrderAdapter provideAdapter() {return new HistoryOrderAdapter(new ArrayList<>()); }


}
