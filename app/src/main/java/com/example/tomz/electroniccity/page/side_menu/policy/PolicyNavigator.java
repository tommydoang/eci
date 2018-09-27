package com.example.tomz.electroniccity.page.side_menu.policy;

public interface PolicyNavigator {

    void onSuccess();
    void onFailed();
    void handleError(Throwable throwable);
}
