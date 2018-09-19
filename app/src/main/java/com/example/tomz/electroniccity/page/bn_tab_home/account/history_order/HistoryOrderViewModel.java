package com.example.tomz.electroniccity.page.bn_tab_home.account.history_order;

import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HistoryOrderViewModel extends BaseViewModel<HistoryOrderNavigator> {

    public HistoryOrderViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void getAllHistoryOrder(String idCustomer){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doGetAllHistoryOrder(new HistoryOrderRequest.req(idCustomer))
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(jsonObject -> {
                    if (jsonObject.getString("status").equals("SUCCSESS")||
                            jsonObject.getString("status").equals("SUCCESS")){
                        setIsLoading(false);
                        setHistoryOrderModel(jsonObject);
                    } else {
                        setIsLoading(false);
                        getNavigator().onFailedHistoryOrder(jsonObject.getString("status"));
                    }
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                })
        );
    }

    private void setHistoryOrderModel(JSONObject jsonObject){
        try{
            JSONObject objResponse = jsonObject.getJSONObject("result");
            JSONArray arrResponse = objResponse.getJSONArray("invoice");
            int arrResponseSize = arrResponse.length();
            Log.d("arrReponseSize tes1", arrResponseSize+"");
            Log.d("noInvoice tes1", arrResponse.getJSONObject(1).getString("Bstnk"));
        } catch (JSONException e){
            Log.e("errJSONOrder tes1", e.getMessage()+"");
            getNavigator().handleError(e.fillInStackTrace());
        }
    }

}
