package com.example.tomz.electroniccity.page.side_menu.promo;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model.api.promo.DataPromoResponse;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PromoViewModel extends BaseViewModel<PromoNavigator> {

    private List<DataPromoResponse> promoList = new ArrayList<>();
    private final MutableLiveData<List<DataPromoResponse>> promoData;
    private final ObservableList<DataPromoResponse> promoDataList = new ObservableArrayList<>();

    public PromoViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        promoData = new MutableLiveData<>();
        getAllDataPromo();
    }

    private void getAllDataPromo(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doGetBannerApiCall()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
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
                    getNavigator().handleError(throwable);
                })
        );
    }

    public MutableLiveData<List<DataPromoResponse>> getPromoLiveDataList() {
        return promoData;
    }

    public ObservableList<DataPromoResponse> getPromoDataList() {
        return promoDataList;
    }

    public void setDataToList(List<DataPromoResponse> dataListApi){
        promoDataList.clear();
        promoDataList.addAll(dataListApi);
    }

    private void setBannerModel(JSONObject jsonObject){
        try{
            JSONObject objResponse = jsonObject.getJSONObject("response");
            Log.i("bannerMDL tes1", "MASUKK!!");
            int objResponseSize = objResponse.length();
            if (objResponseSize > 0) {
                for (int idx = 0; idx < objResponseSize; idx++) {
                    JSONObject subObjResponse = objResponse.getJSONObject(String.valueOf(idx));
                    if (subObjResponse.getString("meta_title").equals("100")) {
                        DataPromoResponse dpr = new DataPromoResponse();
                        dpr.setId_banner(subObjResponse.getString("id_banner"));
                        dpr.setPriority(subObjResponse.getString("priority"));
                        dpr.setName(subObjResponse.getString("name"));
                        dpr.setType(subObjResponse.getString("type"));
                        dpr.setDesc(subObjResponse.getString("desc"));
                        dpr.setLink(subObjResponse.getString("link"));
                        dpr.setImage_banner(subObjResponse.getString("image"));
                        dpr.setImage_mobile(subObjResponse.getString("image_mobile"));
                        dpr.setImage_apps(subObjResponse.getString("image_apps"));
                        dpr.setStartDateTime(subObjResponse.getString("startDateTime"));
                        dpr.setEndDateTime(subObjResponse.getString("endDateTime"));
                        dpr.setIsPublish(subObjResponse.getString("isPublish"));
                        promoList.add(dpr);
                    } else {
                        continue;
                    }
                }
                promoData.setValue(promoList);
            }
        }catch (JSONException e){
            Log.e("errJSONBanner tes1", e.getMessage()+"");
            getNavigator().handleError(e.fillInStackTrace());
        }
    }


}
