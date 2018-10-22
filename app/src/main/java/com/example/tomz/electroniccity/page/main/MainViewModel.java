package com.example.tomz.electroniccity.page.main;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.page.bn_tab_home.account.AuthRequest;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import org.json.JSONObject;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    MainViewModel(DataManager dataManager,
                  SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void getAuthApiCall(String key){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doAuthApiCall(new AuthRequest.req(key))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    JSONObject object = jsonObject.getJSONObject("response");
                    getDataManager().authInfo(
                            object.getString("divice_id"),
                            object.getString("token"));
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                } )
        );
    }

}
