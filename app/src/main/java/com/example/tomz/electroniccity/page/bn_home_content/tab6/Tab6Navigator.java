package com.example.tomz.electroniccity.page.bn_home_content.tab6;

import com.example.tomz.electroniccity.data.model.api.products.tab6.DataProductTab6Response;

import java.util.List;

public interface Tab6Navigator {

    void handleErrorProduct(Throwable throwable);
    void handleErrorBanner(Throwable throwable);
    void onSuccessBanner(String status);
    void onFailedBanner();
    void updateList(List<DataProductTab6Response> dataProductTab6ResponseList);

}
