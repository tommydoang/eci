package com.example.tomz.electroniccity.page.splash;

import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.helper.ToastHelper;
import com.example.tomz.electroniccity.page.bn_tab_home.account.AuthRequest;
import com.example.tomz.electroniccity.page.intro.IntroApps;
import com.example.tomz.electroniccity.page.main.MainActivity;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import org.json.JSONObject;

import io.reactivex.functions.Consumer;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
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
                    getDataManager().authInfo(
                            object.getString("divice_id"),
                            object.getString("token"));
                    setIsLoading(false);
                    getNavigator().openIntroMainActivity(jsonObject.getString("status"));
                }, throwable -> {
                    Log.e("err accept tes1", throwable.getMessage()+"");
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                } )
        );
    }

}
