package com.example.tomz.electroniccity.page.details.cart;

import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;
import com.example.tomz.electroniccity.data.model.db.shop.DBShopListResponse;

import java.util.List;

public interface CartNavigator {

    void onSuccessAddress();
    void onFailedAddress(String msg);
    void handleError(Throwable throwable);
    void updateCartAddressList(List<DataAddressResponse> dataAddressResponseList);
}
