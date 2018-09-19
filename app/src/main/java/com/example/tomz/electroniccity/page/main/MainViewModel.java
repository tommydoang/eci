package com.example.tomz.electroniccity.page.main;

import android.util.Log;

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

    public void getAuthApiCall(String key){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doAuthApiCall(new AuthRequest.req(key))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    JSONObject object = jsonObject.getJSONObject("response");
                    Log.d("accept tes1 3", "MAIN " +object.getString("divice_id"));
                    getDataManager().authInfo(
                            object.getString("divice_id"),
                            object.getString("token"));
                    setIsLoading(false);
                    getNavigator().onSuccessGetAuthKey(jsonObject.getString("status"));
                }, throwable -> {
                    Log.e("err accept tes1", "MAIN " + throwable.getMessage());
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                } )
        );
    }

}
