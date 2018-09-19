package com.example.tomz.electroniccity.page.bn_home_content.tab8;

import com.example.tomz.electroniccity.data.model.api.products.tab8.DataProductTab8Response;

import java.util.List;

public interface Tab8Navigator {

    void handleErrorProduct(Throwable throwable);
    void handleErrorBanner(Throwable throwable);
    void onSuccessBanner(String status);
    void onFailedBanner();
    void updateList(List<DataProductTab8Response> dataProductTab8ResponseList);

}
