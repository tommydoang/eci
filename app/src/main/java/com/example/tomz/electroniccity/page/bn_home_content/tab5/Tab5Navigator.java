package com.example.tomz.electroniccity.page.bn_home_content.tab5;

import com.example.tomz.electroniccity.data.model.api.products.tab4.DataProductTab4Response;
import com.example.tomz.electroniccity.data.model.api.products.tab5.DataProductTab5Response;

import java.util.List;

public interface Tab5Navigator {

    void handleErrorProduct(Throwable throwable);
    void handleErrorBanner(Throwable throwable);
    void onSuccessBanner(String status);
    void onFailedBanner();
    void updateList(List<DataProductTab5Response> dataProductTab5ResponseList);

}
