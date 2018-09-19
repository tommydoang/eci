package com.example.tomz.electroniccity.page.bn_home_content.tab9;

import com.example.tomz.electroniccity.data.model.api.products.tab9.DataProductTab9Response;

import java.util.List;

public interface Tab9Navigator {

    void handleErrorProduct(Throwable throwable);
    void handleErrorBanner(Throwable throwable);
    void onSuccessBanner(String status);
    void onFailedBanner();
    void updateList(List<DataProductTab9Response> dataProductTab9ResponseList);

}
