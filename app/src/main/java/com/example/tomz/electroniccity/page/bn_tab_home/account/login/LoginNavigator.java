package com.example.tomz.electroniccity.page.bn_tab_home.account.login;

public interface LoginNavigator {

    void handleError(Throwable throwable);
    void login();
    void onSuccessLogin(String status);
    void onFailedLogin(String status, String message);
//    void onSuccessActivate(String status);

}
