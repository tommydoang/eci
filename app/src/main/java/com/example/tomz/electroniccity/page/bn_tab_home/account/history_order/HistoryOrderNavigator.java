package com.example.tomz.electroniccity.page.bn_tab_home.account.history_order;

import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;
import com.example.tomz.electroniccity.data.model.api.membership.DataHistoryOrderResponse;

import java.util.List;

public interface HistoryOrderNavigator {

    void onSuccessHistoryOrder(String status);
    void onFailedHistoryOrder(String status);
    void handleError(Throwable throwable);
    void updateList(List<DataHistoryOrderResponse> dataHistoryOrderResponseList);

}
