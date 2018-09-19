package com.example.tomz.electroniccity.page.bn_tab_home.account.register_forget;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class RegForgModule {

    @Provides
    RegForgViewModel provideRegForgViewModel(DataManager dataManager,
                                             SchedulerProvider schedulerProvider){
        return new RegForgViewModel(dataManager, schedulerProvider);
    }

}
