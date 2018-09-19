package com.example.tomz.electroniccity.page.bn_home_content.tab7;

import com.example.tomz.electroniccity.data.model.api.products.tab7.DataProductTab7Response;

import java.util.List;

public interface Tab7Navigator {

    void handleErrorProduct(Throwable throwable);
    void handleErrorBanner(Throwable throwable);
    void onSuccessBanner(String status);
    void onFailedBanner();
    void updateList(List<DataProductTab7Response> dataProductTab7ResponseList);

}
