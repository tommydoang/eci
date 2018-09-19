package com.example.tomz.electroniccity.page.bn_home_content.tab4;

import com.example.tomz.electroniccity.data.model.api.products.tab4.DataProductTab4Response;

import java.util.List;

public interface Tab4Navigator {

    void handleErrorProduct(Throwable throwable);
    void handleErrorBanner(Throwable throwable);
    void onSuccessBanner(String status);
    void onFailedBanner();
    void updateList(List<DataProductTab4Response> dataProductTab4ResponseList);

}
