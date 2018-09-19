package com.example.tomz.electroniccity.page.bn_home_content.tab1;

import com.example.tomz.electroniccity.data.model.api.products.tab1.DataDealTab1Response;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataProductTab1Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public interface Tab1Navigator {

    void handleErrorProduct(Throwable throwable);
    void handleErrorBanner(Throwable throwable);
    void handleErrorDeal(Throwable throwable);
    void onSuccessBanner(String status);
    void onFailedProduct();
    void onFailedBanner();
    void onFailedDeal();
    void updateList(List<DataProductTab1Response> dataProductTab1ResponseList);
    void updateDealList(List<DataDealTab1Response> dataDealTab1ResponseList);

}
