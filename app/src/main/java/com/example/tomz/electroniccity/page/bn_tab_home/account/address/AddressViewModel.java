package com.example.tomz.electroniccity.page.bn_tab_home.account.address;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataProductTab1Response;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddressViewModel extends BaseViewModel<AddressNavigator> {

    private List<DataAddressResponse> addresslist = new ArrayList<>();
    private final MutableLiveData<List<DataAddressResponse>> addressData;
    private final ObservableList<DataAddressResponse> addressDataList = new ObservableArrayList<>();

    public AddressViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        addressData = new MutableLiveData<>();
    }

    public void addressRequest(String idCustomer){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doGetAllAddressApiCall(new AllAddressRequest.req(idCustomer))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    if (jsonObject.getString("status").equals("SUCCESS")){
                        setIsLoading(false);
                        setAddressModel(jsonObject);
                    }
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                })
        );
    }

    public MutableLiveData<List<DataAddressResponse>> getLiveAddressDataList() {
        return addressData;
    }

    public ObservableList<DataAddressResponse> getAddressDataList() {
        return addressDataList;
    }

    public void setDataToList(List<DataAddressResponse> dataListApi){
        addressDataList.clear();
        addressDataList.addAll(dataListApi);
    }

    private void setAddressModel(JSONObject jsonObject){
        try{
            JSONArray arrResult = jsonObject.getJSONArray("result");
            int arrResultSize = arrResult.length();
            Log.d("arrAddrSize tes1", arrResultSize+"");
            for (int idx = 0; idx < arrResultSize; idx++){
                JSONObject objResult = arrResult.getJSONObject(idx);
                DataAddressResponse dar = new DataAddressResponse();
                dar.setIdCustomer(objResult.getString("id_customer"));
                dar.setIdCustomerAddress(objResult.getString("id_customer_address"));
                dar.setTitleAddress(objResult.getString("title_address"));
                dar.setNameReceiver(objResult.getString("name"));
                dar.setPhoneNumber1(objResult.getString("phone_number"));
                dar.setPhoneNumber2(objResult.getString("phone_number2"));
                dar.setAddress(objResult.getString("address"));
                dar.setIsDefault(objResult.getString("isDefault"));
                dar.setIdDistrict(objResult.getString("id_district"));
                dar.setIsPrimary(objResult.getString("isPrimary"));
                if (!objResult.isNull("id_wedding_registry")) {
                    dar.setIdWeddingRegistry(objResult.getString("id_wedding_registry"));
                } else {
                    dar.setIdWeddingRegistry("");
                }
                addresslist.add(dar);
            }
            addressData.setValue(addresslist);
        } catch (Exception e){
            Log.e("errJSONProd tes1", e.getMessage()+"");
            getNavigator().handleError(e.fillInStackTrace());
        }

    }

}

