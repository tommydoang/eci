package com.example.tomz.electroniccity.page.bn_tab_home.account.history_order;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class HistoryOrderModule {

    @Provides
    HistoryOrderViewModel provideHistoryOrderViewModel(DataManager dataManager,
                                                       SchedulerProvider schedulerProvider){
        return new HistoryOrderViewModel(dataManager, schedulerProvider);
    }

}
