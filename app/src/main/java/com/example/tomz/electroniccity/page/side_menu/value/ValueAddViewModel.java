package com.example.tomz.electroniccity.page.side_menu.value;

import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model.api.valueadded.DataValueAddResponse;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ValueAddViewModel extends BaseViewModel<ValueAddNavigator> {

    private List<DataValueAddResponse> valueList = new ArrayList<>();

    ValueAddViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void getDataValueAddMenu(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doGetAllValueAddedApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    if (jsonObject.getString("status").equals("SUCCESS")||
                            jsonObject.getString("status").equals("SUCCSESS")){
                        setIsLoading(false);
                        setValueAddModel(jsonObject);
                    } else {
                        setIsLoading(false);
                        getNavigator().onFailed(jsonObject.getString("status"));
                    }
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                })
        );
    }

    private void setValueAddModel(JSONObject jsonObject){
        try{
            JSONObject objResponse = jsonObject.getJSONObject("response");
            int objResponseSize = objResponse.length();
            if (objResponseSize > 0){
                for (int idx = 0; idx < objResponseSize; idx++){
                    JSONObject objResponseIdx = objResponse.getJSONObject(String.valueOf(idx));
                    DataValueAddResponse dvar = new DataValueAddResponse();
                    dvar.setId_value(objResponseIdx.getString("id"));
                    dvar.setTitle_value(objResponseIdx.getString("title"));
                    dvar.setDescription_value(objResponseIdx.getString("description"));
                    dvar.setImage_value(objResponseIdx.getString("image"));
                    dvar.setIsPremium(objResponseIdx.getString("isPremium"));
                    dvar.setIsPublish(objResponseIdx.getString("isPublish"));
                    dvar.setSortTo(objResponseIdx.getString("sort"));

                    valueList.add(dvar);
                }
                getNavigator().onSuccess(valueList);
            }
        } catch (JSONException e){
            Log.e("errJSONBanner tes1", e.getMessage()+"");
        }
    }

}
