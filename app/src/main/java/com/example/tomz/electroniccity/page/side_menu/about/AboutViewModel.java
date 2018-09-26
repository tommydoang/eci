package com.example.tomz.electroniccity.page.side_menu.about;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model.api.about.DataAboutUsResponse;
import com.example.tomz.electroniccity.data.model.api.promo.DataPromoResponse;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AboutViewModel extends BaseViewModel<AboutNavigator> {

    private List<DataAboutUsResponse> aboutList = new ArrayList<>();
    private final MutableLiveData<List<DataAboutUsResponse>> aboutData;
    private final ObservableList<DataAboutUsResponse> aboutDataList = new ObservableArrayList<>();

    public AboutViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        aboutData = new MutableLiveData<>();
        getDataAboutUsMenu();
    }

    private void getDataAboutUsMenu(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doGetAboutUsApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    if (jsonObject.getString("status").equals("SUCCESS") ||
                            jsonObject.getString("status").equals("SUCCSESS")){
                        setIsLoading(false);
                        setAboutModel(jsonObject);
                    } else {
                        setIsLoading(false);
                        getNavigator().onFailed();

                    }
                },throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                })
        );
    }

    public MutableLiveData<List<DataAboutUsResponse>> getAboutLiveDataList() {
        return aboutData;
    }

    public ObservableList<DataAboutUsResponse> getAboutDataList() {
        return aboutDataList;
    }

    public void setDataToList(List<DataAboutUsResponse> dataListApi){
        aboutDataList.clear();
        aboutDataList.addAll(dataListApi);
    }

    private void setAboutModel(JSONObject jsonObject){
        try{
            JSONObject objResponse = jsonObject.getJSONObject("response");
            int objResponseSize = objResponse.length();
            if (objResponseSize > 0){
                for (int idx = 0; idx < objResponseSize; idx++){
                    JSONObject objResponseIdx = objResponse.getJSONObject(idx+"");
                    DataAboutUsResponse daur = new DataAboutUsResponse();
                    daur.setId(objResponseIdx.getString("id"));
                    daur.setTitle(objResponseIdx.getString("title"));
                    daur.setDescription(objResponseIdx.getString("description"));
                    daur.setImage(objResponseIdx.getString("image"));
                    daur.setTab(objResponseIdx.getString("tab"));
                    daur.setSort(objResponseIdx.getString("sort"));
                    daur.setPublish(objResponseIdx.getString("isPublish"));

                    Log.d("title tes1", objResponseIdx.getString("title"));
                    aboutList.add(daur);
                }
                aboutData.setValue(aboutList);
            }
        } catch (JSONException e){
            Log.e("errJSONBanner tes1", e.getMessage()+"");
            getNavigator().handleError(e.fillInStackTrace());
        }
    }

}
