package com.example.tomz.electroniccity.page.bn_tab_home.account.edit_profile;

import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.helper.ToastHelper;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

public class EditProfileViewModel extends BaseViewModel<EditProfileNavigator> {

    public EditProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void savedProfileChange(){
        setIsLoading(true);
        //call backend to save profile change
    }

}
