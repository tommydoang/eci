package com.example.tomz.electroniccity.page.bn_tab_home.account.history_order;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;
import com.example.tomz.electroniccity.data.model.api.membership.DataHistoryOrderResponse;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryOrderViewModel extends BaseViewModel<HistoryOrderNavigator> {

    private List<DataHistoryOrderResponse> historyOrderList = new ArrayList<>();
    private final MutableLiveData<List<DataHistoryOrderResponse>> historyOrderData;
    private final ObservableList<DataHistoryOrderResponse> historyOrderDataList = new ObservableArrayList<>();

    public HistoryOrderViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        historyOrderData = new MutableLiveData<>();
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

    public MutableLiveData<List<DataHistoryOrderResponse>> getLiveHistoryOrderDataList() {
        return historyOrderData;
    }

    public ObservableList<DataHistoryOrderResponse> getHistoryOrderDataList() {
        return historyOrderDataList;
    }

    public void setDataToList(List<DataHistoryOrderResponse> dataListApi){
        historyOrderDataList.clear();
        historyOrderDataList.addAll(dataListApi);
    }

    private void setHistoryOrderModel(JSONObject jsonObject){
        try{
            JSONObject objResponse = jsonObject.getJSONObject("result");
            JSONArray arrResponse = objResponse.getJSONArray("invoice");
            int arrResponseSize = arrResponse.length();
            Log.d("arrReponseSize tes1", arrResponseSize+"");
            if (arrResponseSize > 0) {
//                Log.d("noInvoice tes1", arrResponse.getJSONObject(1).getString("Bstnk"));
                for (int idx = 0; idx < arrResponseSize; idx++){
                    DataHistoryOrderResponse dhor = new DataHistoryOrderResponse();
                    dhor.setVbeln(arrResponse.getJSONObject(idx).getString("Vbeln"));
                    dhor.setBstnk(arrResponse.getJSONObject(idx).getString("Bstnk"));
                    dhor.setKunnr(arrResponse.getJSONObject(idx).getString("Kunnr"));
                    dhor.setName1(arrResponse.getJSONObject(idx).getString("Name1"));
                    dhor.setZaddr1(arrResponse.getJSONObject(idx).getString("Zaddr1"));
                    dhor.setAudat(arrResponse.getJSONObject(idx).getString("Audat"));
                    dhor.setNetwr(arrResponse.getJSONObject(idx).getString("Netwr"));
                    JSONArray detailArrResponse = arrResponse.getJSONObject(idx).getJSONArray("detail");
                    int detailArrSize = detailArrResponse.length();
                    for (int idx2 = 0; idx2 < detailArrSize; idx2++) {
                        Log.d("detailARR tes1", idx + idx2 + detailArrResponse.getJSONObject(idx2).getString("Maktx"));
                        dhor.setDtlMatnr(detailArrResponse.getJSONObject(idx2).getString("Matnr"));
                        dhor.setDtlMaktx(detailArrResponse.getJSONObject(idx2).getString("Maktx"));
                        dhor.setDtlUmziz(detailArrResponse.getJSONObject(idx2).getString("Umziz"));
                        if (!detailArrResponse.getJSONObject(idx2).isNull("image")) {
                            Log.d("detailARR_IMG tes1", detailArrResponse.getJSONObject(idx2).getString("image"));
                            dhor.setDtlImage(detailArrResponse.getJSONObject(idx2).getString("image"));
                        } else {
                            Log.e("dtlIMG tes1", "NULLL!!!");
                        }
                    }
                    historyOrderList.add(dhor);
                }
                historyOrderData.setValue(historyOrderList);
            }
        } catch (JSONException e){
            Log.e("errJSONOrder tes1", e.getMessage()+"");
            getNavigator().handleError(e.fillInStackTrace());
        }
    }

}
