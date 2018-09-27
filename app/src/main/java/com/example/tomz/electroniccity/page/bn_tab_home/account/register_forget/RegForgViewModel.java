package com.example.tomz.electroniccity.page.bn_tab_home.account.register_forget;

import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.helper.ToastHelper;
import com.example.tomz.electroniccity.page.bn_tab_home.account.ForgetPassRequest;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

public class RegForgViewModel extends BaseViewModel<RegForgNavigator> {

    private RegForgNavigator mRegForgNavigator = new RegForgAct() ;

    RegForgViewModel(DataManager dataManager,
                     SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void registerRequest(String fullname, String handphone){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doRegisterApiCall(new RegisterRequest.req(fullname, handphone))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    if (jsonObject.getString("status").equals("SUCCESS") ||
                            jsonObject.getString("status").equals("SUCCSESS")) {
                        setIsLoading(false);
//                        getNavigator().onSuccessRegister(jsonObject.getString("response"));
                        Log.d("REG SUCCESS tes1", "MASUKK!!!");
                    } else {
                        setIsLoading(false);
//                        mRegForgNavigator.onFailedRegister("Maaf server sedang tidak dapat memproses permintaan Anda");
                        Log.d("REG FAILED tes1", "MASUKK!!!");
                    }
                }, throwable -> {
                    setIsLoading(false);
//                    getNavigator().handleError(throwable);
//                    mRegForgNavigator.handleError(throwable);
                    Log.d("REG THROW tes1", "MASUKK!!!");
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
                    if (jsonObject.getString("status").equals("SUCCESS") ||
                            jsonObject.getString("status").equals("SUCCSESS")){
                        setIsLoading(false);
//                        getNavigator().onSuccessResetPass(jsonObject.getString("response"));
                    } else {
                        setIsLoading(false);
//                        getNavigator().onFailedResetPass(jsonObject.getJSONObject("response").getString("msg"));
                    }
                },throwable -> {
                    setIsLoading(false);
//                    getNavigator().handleError(throwable);
                })
        );
    }


}
