package com.example.tomz.electroniccity.page.side_menu.policy;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model.api.about.DataAboutUsResponse;
import com.example.tomz.electroniccity.data.model.api.policy.DataPolicyResponse;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PolicyViewModel extends BaseViewModel<PolicyNavigator> {

    private List<DataPolicyResponse> policyList = new ArrayList<>();
    private final MutableLiveData<List<DataPolicyResponse>> policyData;
    private final ObservableList<DataPolicyResponse> policyDataList = new ObservableArrayList<>();

    public PolicyViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        policyData = new MutableLiveData<>();
        getDataPolicy();
    }

    private void getDataPolicy(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doGetPolicyApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    if (jsonObject.getString("status").equals("SUCCESS") ||
                            jsonObject.getString("status").equals("SUCCSESS")){
                        setIsLoading(false);
                        setPolicyModel(jsonObject);
                    } else {
                        setIsLoading(false);
                        getNavigator().onFailed();
                    }
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                })
        );
    }

    private void setPolicyModel(JSONObject jsonObject){
        try{
            JSONObject objResponse = jsonObject.getJSONObject("response");
            int objResponseSize = objResponse.length();
            if (objResponseSize > 0) {
                for (int idx = 0; idx < objResponseSize; idx++) {
                    JSONObject objResponseIdx = objResponse.getJSONObject(idx + "");
                    DataPolicyResponse dpor = new DataPolicyResponse();
                    dpor.setId_info(objResponseIdx.getString("id_info"));
                    dpor.setParent(objResponseIdx.getString("parent"));
                    dpor.setId_info_type(objResponseIdx.getString("id_info_type"));
                    dpor.setId_info_position(objResponseIdx.getString("id_info_position"));
                    dpor.setLink(objResponseIdx.getString("link"));
                    dpor.setTitle(objResponseIdx.getString("title"));
                    dpor.setDescription(objResponseIdx.getString("desc"));
                    policyList.add(dpor);
                }
                policyData.setValue(policyList);
            }
        }catch (JSONException e){
            Log.e("errJSONPolicy tes1", e.getMessage());
            getNavigator().handleError(e.fillInStackTrace());
        }
    }

}
