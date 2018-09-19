package com.example.tomz.electroniccity.page.bn_tab_home.account;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.page.bn_tab_home.account.login.LoginNavigator;
import com.example.tomz.electroniccity.page.bn_tab_home.account.login.LoginRequest;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import org.json.JSONArray;
import org.json.JSONObject;

public class FragAccViewModel extends BaseViewModel<LoginNavigator> {

    FragAccViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void loginRequest(String userName, String userPassword){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doLoginApiCall(new LoginRequest.req(userName,userPassword))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    JSONObject object = jsonObject.getJSONObject("result");
                    if (jsonObject.getString("status").equals("SUCCESS")) {
                        getDataManager().userInfo(
                                object.getString("id_customer"),
                                object.getString("username"),
                                object.getString("identityID"),
                                object.getString("email"),
                                object.getString("fullname"),
                                object.getString("birthday"),
                                object.getString("phone_number"),
                                object.getString("handphone"),
                                object.getString("address"),
                                object.getString("address2"),
                                object.getString("address3"),
                                object.getString("address4"),
                                object.getString("id_districts"),
                                object.getString("kode_pos"),
                                object.getString("id_rule"),
                                object.getString("id_type_customer"),
                                object.getString("id_reputation"),
                                object.getString("sap_code"),
                                object.getString("activation_date"),
                                object.getString("channel"));
                        getNavigator().onSuccessLogin(jsonObject.getString("status"));
                    } else {
                        JSONArray array = object.getJSONArray("password");
                        getNavigator().onFailedLogin(jsonObject.getString("status"),
                                array.get(0).toString());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                })
        );
    }

    public void onLoginClicked(){
        getNavigator().login();
    }

}
