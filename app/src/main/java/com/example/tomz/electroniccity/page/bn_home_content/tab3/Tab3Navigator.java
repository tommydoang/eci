package com.example.tomz.electroniccity.page.bn_home_content.tab3;

import com.example.tomz.electroniccity.data.model.api.products.tab3.DataProductTab3Response;

import java.util.List;

public interface Tab3Navigator {

    void handleErrorProduct(Throwable throwable);
    void handleErrorBanner(Throwable throwable);
    void onSuccessBanner(String status);
    void onFailedBanner();
    void updateList(List<DataProductTab3Response> dataProductTab3ResponseList);

}
