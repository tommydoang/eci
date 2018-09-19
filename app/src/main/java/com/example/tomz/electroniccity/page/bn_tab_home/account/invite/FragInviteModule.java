package com.example.tomz.electroniccity.page.bn_tab_home.account.invite;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class FragInviteModule {

    @Provides
    FragInviteViewModel provideFragInviteViewModel(DataManager dataManager,
                                                   SchedulerProvider schedulerProvider){
        return new FragInviteViewModel(dataManager, schedulerProvider);
    }
}
