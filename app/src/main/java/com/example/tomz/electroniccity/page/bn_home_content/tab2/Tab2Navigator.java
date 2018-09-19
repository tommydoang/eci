package com.example.tomz.electroniccity.page.bn_home_content.tab2;

import com.example.tomz.electroniccity.data.model.api.products.tab2.DataProductTab2Response;

import java.util.List;

public interface Tab2Navigator {

    void handleErrorProduct(Throwable throwable);
    void handleErrorBanner(Throwable throwable);
    void onSuccessBanner(String status);
    void onFailedBanner();
    void updateList(List<DataProductTab2Response> dataProductTab2ResponseList);

}
