package com.example.tomz.electroniccity.page.bn_tab_home.account.register_forget;

public interface RegForgNavigator {
    void onRegister();
    void onSuccessRegister(String message);
    void onFailedRegister(String message);
    void handleError(Throwable throwable);
    void onResetPass();
    void onSuccessResetPass(String message);
    void onFailedResetPass(String message);
}
