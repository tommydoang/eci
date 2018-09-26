package com.example.tomz.electroniccity.page.side_menu.about;

import com.example.tomz.electroniccity.data.model.api.about.DataAboutUsResponse;
import com.example.tomz.electroniccity.data.model.api.promo.DataPromoResponse;

import java.util.List;

public interface AboutNavigator {

    void onSuccess();
    void onFailed();
    void handleError(Throwable throwable);
    void updateList(List<DataAboutUsResponse> dataAboutUsResponseList);

}
