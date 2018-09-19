package com.example.tomz.electroniccity.page.side_menu.promo;

import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;
import com.example.tomz.electroniccity.data.model.api.promo.DataPromoResponse;

import java.util.List;

public interface PromoNavigator {

    void handleError(Throwable throwable);
    void onFailedBanner();
    void updateList(List<DataPromoResponse> dataPromoResponseList);

}
