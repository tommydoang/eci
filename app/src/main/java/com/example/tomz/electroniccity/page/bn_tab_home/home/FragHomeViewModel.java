package com.example.tomz.electroniccity.page.bn_tab_home.home;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

public class FragHomeViewModel extends BaseViewModel<FragHomeNavigator> {

    public FragHomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

}
