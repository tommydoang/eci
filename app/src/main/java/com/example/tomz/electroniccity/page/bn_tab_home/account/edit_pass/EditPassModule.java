package com.example.tomz.electroniccity.page.bn_tab_home.account.edit_pass;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class EditPassModule {

    @Provides
    EditPassViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new EditPassViewModel(dataManager, schedulerProvider);
    }

}
