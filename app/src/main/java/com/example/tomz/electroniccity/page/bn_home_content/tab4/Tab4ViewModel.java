package com.example.tomz.electroniccity.page.bn_home_content.tab4;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Build;
import android.text.Html;
import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model.api.products.tab4.DataBannerTab4Response;
import com.example.tomz.electroniccity.data.model.api.products.tab4.DataProductTab4Response;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tab4ViewModel extends BaseViewModel<Tab4Navigator> {

    private List<DataProductTab4Response> productsList = new ArrayList<>();
    private List<DataBannerTab4Response> bannerList = new ArrayList<>();
    private final MutableLiveData<List<DataProductTab4Response>> tab4Data;
    private final ObservableList<DataProductTab4Response> tab4DataList = new ObservableArrayList<>();

    public Tab4ViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        tab4Data = new MutableLiveData<>();
        getDataFromServer();
    }

    public MutableLiveData<List<DataProductTab4Response>> getImageLiveDataList() {
        return tab4Data;
    }

    public ObservableList<DataProductTab4Response> getTab4DataList() {
        return tab4DataList;
    }

    public void setDataToList(List<DataProductTab4Response> dataListApi){
        tab4DataList.clear();
        tab4DataList.addAll(dataListApi);
    }

    public List<DataBannerTab4Response> getAllBannerList(){
        return bannerList;
    }

    private void getDataFromServer(){
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
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleErrorBanner(throwable);
                })
        );
    }

    private void setProductModel(JSONArray jsonArray){
        try{
            Log.d("allProduc tes1 tab4", "MAIN VM " + jsonArray.length()+"");
            int jsonSize = jsonArray.length();
            for (int idx = 0; idx < jsonSize; idx++){
                JSONObject object = jsonArray.getJSONObject(idx);
                if (object.getString("id_category_head").equals("3")) {
                    JSONArray arrProd = object.getJSONArray("products");
                    int arrProdSize = arrProd.length();
                    Log.d("arrProdSize tes1", arrProdSize+"");
                    for (int idx2 = 0; idx2 < arrProdSize; idx2++) {
                        JSONObject objProd = arrProd.getJSONObject(idx2);
                        Log.d("skuProd tes1 tab2", objProd.getString("sku"));

                        DataProductTab4Response dpr = new DataProductTab4Response();
                        dpr.setId_category_head(object.getString("id_category_head"));
                        dpr.setId_prod(objProd.getString("id_prod"));
                        dpr.setSku(objProd.getString("sku"));
                        dpr.setName_prod(objProd.getString("name_prod"));
                        if (!objProd.isNull("tags")) {
                            dpr.setTags(objProd.getString("tags"));
                        } else {
                            Log.d("tagProd  tab2", "MASUKKK!!!");
                            dpr.setTags("");
                        }
                        dpr.setModel_prod(objProd.getString("model"));
                        dpr.setId_brand(objProd.getString("id_brand"));
                        dpr.setId_cat(objProd.getString("id_cat"));
                        dpr.setId_cat_new(objProd.getString("id_cat_new"));
                        dpr.setProduct_description(objProd.getString("prod_desc"));
//                        dpr.setLong_description(objProd.getString("long_desc"));
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
                        Log.d("numStock tes1 tab4", objStock.getString("stock"));

                        productsList.add(dpr);
                    }
                    break;
                } else {
                    continue;
                }
            }
            tab4Data.setValue(productsList);
        } catch (JSONException e){
            Log.e("errJSONProd tes1", e.getMessage()+"");
            getNavigator().handleErrorProduct(e.fillInStackTrace());
        }
    }

    private void setBannerModel(JSONObject jsonObject){
        try{
            JSONObject objResponse = jsonObject.getJSONObject("response");
            int objResponseSize = objResponse.length();
            if (objResponseSize > 0){
                for (int idx = 0; idx < objResponseSize; idx++){
                    JSONObject subObjResponse = objResponse.getJSONObject(String.valueOf(idx));
                    if (subObjResponse.getString("meta_title").equals("3")) {
                        DataBannerTab4Response dbr = new DataBannerTab4Response();
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



}
