package com.example.tomz.electroniccity.page.bn_tab_home.account.history_order;

public interface HistoryOrderNavigator {

    void onSuccessHistoryOrder(String status);
    void onFailedHistoryOrder(String status);
    void handleError(Throwable throwable);

}
