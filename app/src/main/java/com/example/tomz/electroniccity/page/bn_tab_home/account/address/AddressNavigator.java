package com.example.tomz.electroniccity.page.bn_tab_home.account.address;

import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;

import java.util.List;

public interface AddressNavigator {

    void handleError(Throwable throwable);
    void updateList(List<DataAddressResponse> dataAddressResponseList);

}
