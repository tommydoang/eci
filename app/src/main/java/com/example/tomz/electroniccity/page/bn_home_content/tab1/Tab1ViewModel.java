package com.example.tomz.electroniccity.page.bn_home_content.tab1;

import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Html;
import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataBannerTab1Response;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataDealTab1Response;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataProductTab1Response;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tab1ViewModel extends BaseViewModel<Tab1Navigator> {

    private List<DataProductTab1Response> productsList = new ArrayList<>();
    private List<DataBannerTab1Response> bannerList = new ArrayList<>();
    private List<DataDealTab1Response> dealList = new ArrayList<>();
    private final MutableLiveData<List<DataProductTab1Response>> tab1Data;
    private final ObservableList<DataProductTab1Response> tab1DataList = new ObservableArrayList<>();
    private final MutableLiveData<List<DataDealTab1Response>> tab1DataDeal;
    private final ObservableList<DataDealTab1Response> tab1DataDealList = new ObservableArrayList<>();

    public Tab1ViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        tab1Data = new MutableLiveData<>();
        tab1DataDeal = new MutableLiveData<>();
        getDatafromServer();
        getDataSuperDeal();
    }

    private void getDatafromServer(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doAllProductsApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonArray -> {
                    setIsLoading(false);
                    setProductModel(jsonArray);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleErrorProduct(throwable);
                })
        );
    }

    public void getDataBanner(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doGetBannerApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    if (jsonObject.getString("status").equals("SUCCSESS")||
                            jsonObject.getString("status").equals("SUCCESS")) {
                        setIsLoading(false);
                        setBannerModel(jsonObject);
                    } else {
                        setIsLoading(false);
                        getNavigator().onFailedBanner();
                    }
                },throwable -> {
                    setIsLoading(false);
                    getNavigator().handleErrorBanner(throwable);
                } )
        );
    }

    private void getDataSuperDeal(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doGetDealApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    if (jsonObject.getString("status").equals("SUCCSESS")||
                            jsonObject.getString("status").equals("SUCCESS")) {
                        setIsLoading(false);
                        setDealModel(jsonObject);
                    } else {
                        setIsLoading(false);
                        getNavigator().onFailedDeal();
                    }
                },throwable -> {
                    setIsLoading(false);
                    getNavigator().handleErrorDeal(throwable);
                })
        );
    }

    public MutableLiveData<List<DataProductTab1Response>> getImageLiveDataList() {
        return tab1Data;
    }

    public ObservableList<DataProductTab1Response> getTab1DataList() {
        return tab1DataList;
    }

    public MutableLiveData<List<DataDealTab1Response>> getImageLiveDataDealList() {
        return tab1DataDeal;
    }

    public ObservableList<DataDealTab1Response> getTab1DataDealList() {
        return tab1DataDealList;
    }

    public void setDataToList(List<DataProductTab1Response> dataListApi){
        tab1DataList.clear();
        tab1DataList.addAll(dataListApi);
    }

    public void setDataDealToList(List<DataDealTab1Response> dataListApi){
        tab1DataDealList.clear();
        tab1DataDealList.addAll(dataListApi);
    }

    public List<DataBannerTab1Response> getAllBannerList(){
        return bannerList;
    }

    private void setProductModel(JSONArray jsonArray){
        try{
            Log.d("allProduc tes1", "MAIN VM " + jsonArray.length()+"");
            int jsonSize = jsonArray.length();
            for (int idx = 0; idx < jsonSize; idx++){
                JSONObject object = jsonArray.getJSONObject(idx);
                if (object.getString("id_category_head").equals("0")) {
                    JSONArray arrProd = object.getJSONArray("products");
                    int arrProdSize = arrProd.length();
                    for (int idx2 = 0; idx2 < arrProdSize; idx2++) {
                        JSONObject objProd = arrProd.getJSONObject(idx2);
//                        Log.d("skuProd tes1", objProd.getString("sku"));

                        DataProductTab1Response dpr = new DataProductTab1Response();
                        dpr.setId_category_head(object.getString("id_category_head"));
                        dpr.setId_prod(objProd.getString("id_prod"));
                        dpr.setSku(objProd.getString("sku"));
                        dpr.setName_prod(objProd.getString("name_prod"));
                        if (!objProd.isNull("tags")) {
                            dpr.setTags(objProd.getString("tags"));
                        } else {
                            Log.d("tagProd tes1", "MASUKKK!!!");
                            dpr.setTags("");
                        }
                        dpr.setModel_prod(objProd.getString("model"));
                        dpr.setId_brand(objProd.getString("id_brand"));
                        dpr.setId_cat(objProd.getString("id_cat"));
                        dpr.setId_cat_new(objProd.getString("id_cat_new"));
                        dpr.setProduct_description(objProd.getString("prod_desc"));

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            dpr.setLong_description(String.valueOf(Html.fromHtml(objProd.getString("long_desc"), Html.FROM_HTML_MODE_LEGACY)));
                        } else {
                            dpr.setLong_description(String.valueOf(Html.fromHtml(objProd.getString("long_desc"))));
                        }

//                        Log.d("longDesc tes1 1", String.valueOf(Html.fromHtml(objProd.
//                                getString("long_desc"))));
//                        Log.d("longDesc tes1 2", objProd.getString("long_desc"));

                        if (objProd.isNull("spc_price")) {
                            dpr.setSpc_price("");
                        } else {
                            dpr.setSpc_price(objProd.getString("spc_price"));
                        }

                        dpr.setReal_price(objProd.getString("real_price"));
                        dpr.setType_prod(objProd.getString("type_prod"));

                        if (objProd.isNull("delivery_type")) {
                            dpr.setDelivery_type("");
                        } else {
                            dpr.setDelivery_type(objProd.getString("delivery_type"));
                        }

                        dpr.setCity_free_shipping(objProd.getString("city_free_shipping"));
                        dpr.setViewed(objProd.getString("viewed"));
                        dpr.setTurun_harga(objProd.getString("turun_harga"));
                        dpr.setImg_best(objProd.getString("image_best"));
                        dpr.setImg_thumb(objProd.getString("image_thumb"));

                        JSONObject objBrand = objProd.getJSONObject("brand");
                        dpr.setMerk_name_brand(objBrand.getString("name_brand"));

                        JSONObject objStock = objProd.getJSONObject("stock");
                        dpr.setStock_store_code(objStock.getString("store_code"));
                        dpr.setNumber_stock(objStock.getString("stock"));
                        dpr.setStock_item_bought(objStock.getString("useStock"));
//                        Log.d("numStock tes1", objStock.getString("stock"));

                        productsList.add(dpr);
                    }
                } else {
                    break;
                }
            }
            tab1Data.setValue(productsList);
        } catch (JSONException e){
            Log.e("errJSONProd tes1", e.getMessage()+"");
            getNavigator().handleErrorProduct(e.fillInStackTrace());
        }
    }

    private void setBannerModel(JSONObject jsonObject){
        try{
            JSONObject objResponse = jsonObject.getJSONObject("response");
            int objResponseSize = objResponse.length();
            if (objResponseSize > 0) {
                for (int idx = 0; idx < objResponseSize; idx++) {
                    JSONObject subObjResponse = objResponse.getJSONObject(String.valueOf(idx));
                    if (subObjResponse.getString("meta_title").equals("100")) {
                        DataBannerTab1Response dbr = new DataBannerTab1Response();
                        dbr.setId_banner(subObjResponse.getString("id_banner"));
                        dbr.setPriority(subObjResponse.getString("priority"));
                        dbr.setName(subObjResponse.getString("name"));
                        dbr.setType(subObjResponse.getString("type"));
                        dbr.setDesc(subObjResponse.getString("desc"));
                        dbr.setLink(subObjResponse.getString("link"));
                        dbr.setImage_banner(subObjResponse.getString("image"));
                        dbr.setImage_mobile(subObjResponse.getString("image_mobile"));
                        dbr.setImage_apps(subObjResponse.getString("image_apps"));
                        dbr.setStartDateTime(subObjResponse.getString("startDateTime"));
                        dbr.setEndDateTime(subObjResponse.getString("endDateTime"));
                        dbr.setIsPublish(subObjResponse.getString("isPublish"));
                        bannerList.add(dbr);
                    } else {
                        continue;
                    }
                }
                getNavigator().onSuccessBanner(jsonObject.getString("status"));
            }
        }catch (JSONException e){
            Log.e("errJSONBanner tes1", e.getMessage()+"");
            getNavigator().handleErrorBanner(e.fillInStackTrace());
        }
    }

    private void setDealModel(JSONObject jsonObject){
        try{
            JSONObject objResponse = jsonObject.getJSONObject("result");
            int objResponseSize = objResponse.length();
            for (int idx = 0; idx < objResponseSize; idx++){
                JSONObject subObjResponse = objResponse.getJSONObject(String.valueOf(idx));
                DataDealTab1Response ddr = new DataDealTab1Response();
                ddr.setId_banner(subObjResponse.getString("id_banner"));
                ddr.setPriority(subObjResponse.getString("priority"));
                ddr.setName(subObjResponse.getString("name"));
                ddr.setType(subObjResponse.getString("type"));
                ddr.setDesc(subObjResponse.getString("desc"));
                ddr.setLink(subObjResponse.getString("link"));
                ddr.setImage_banner(subObjResponse.getString("image"));
                ddr.setImage_mobile(subObjResponse.getString("image_mobile"));
                ddr.setImage_apps(subObjResponse.getString("image_apps"));
                ddr.setStartDateTime(subObjResponse.getString("startDateTime"));
                ddr.setEndDateTime(subObjResponse.getString("endDateTime"));
                ddr.setIsPublish(subObjResponse.getString("isPublish"));
                dealList.add(ddr);
            }
            tab1DataDeal.setValue(dealList);
        }catch (JSONException e){
            Log.e("errJSONBanner tes1", e.getMessage()+"");
            getNavigator().handleErrorBanner(e.fillInStackTrace());
        }

    }

}
