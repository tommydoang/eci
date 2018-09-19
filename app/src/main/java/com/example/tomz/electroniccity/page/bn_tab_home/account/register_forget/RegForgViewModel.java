package com.example.tomz.electroniccity.page.bn_tab_home.account.register_forget;

import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.page.bn_tab_home.account.ForgetPassRequest;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

public class RegForgViewModel extends BaseViewModel<RegForgNavigator> {

    public RegForgViewModel(DataManager dataManager,
                            SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void registerRequest(/*String email,*/ String fullname, String handphone){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doRegisterApiCall(new RegisterRequest.req(/*email,*/ fullname, handphone))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    if (jsonObject.getString("status").equals("SUCCESS")) {
                        setIsLoading(false);
                        getNavigator().onSuccessRegister(jsonObject.getString("result"));
                    } else {
                        setIsLoading(false);
                        getNavigator().onFailedRegister(jsonObject.getString("result"));
                    }
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                })
        );
    }

    public void resetPasswordRequest(String email){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doForgetPassApiCall(new ForgetPassRequest.req(email))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    if (jsonObject.getString("status").equals("SUCCESS")){
                        setIsLoading(false);
                        getNavigator().onSuccessResetPass(jsonObject.getString("result"));
                    } else {
                        setIsLoading(false);
                        getNavigator().onFailedResetPass(jsonObject.getString("result"));
                    }
                },throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                })
        );
    }

    public void onRegisterClicked(){
        Log.d("viewModel tes1", "CLICKED MASUKK!!!!");
        getNavigator().onRegister();
    }

    public void onForgotPassClicked() { getNavigator().onResetPass();}

}
