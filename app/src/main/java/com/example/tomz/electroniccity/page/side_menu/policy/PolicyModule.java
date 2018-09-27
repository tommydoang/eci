package com.example.tomz.electroniccity.page.side_menu.policy;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class PolicyModule {

    @Provides
    PolicyViewModel provideViewModel(DataManager dataManager,
                                     SchedulerProvider schedulerProvider){
        return new PolicyViewModel(dataManager, schedulerProvider);
    }

}
