package com.example.tomz.electroniccity.page.details.cart;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;
import com.example.tomz.electroniccity.page.bn_tab_home.account.address.DefaultAddressRequest;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends BaseViewModel<CartNavigator> {

    private List<DataAddressResponse> addresslist = new ArrayList<>();
    private final MutableLiveData<List<DataAddressResponse>> cartData;
    private final ObservableList<DataAddressResponse> cartDataList = new ObservableArrayList<>();

    CartViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        cartData = new MutableLiveData<>();
    }

    void addressRequest(String idCustomer, String isDefault){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doGetDefaultAddressApiCall(new DefaultAddressRequest.req(idCustomer, isDefault))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    if (jsonObject.getString("status").equals("SUCCESS")){
                        setIsLoading(false);
                        setAddressModel(jsonObject);
                    } else {
                        setIsLoading(false);
                        getNavigator().onFailedAddress(jsonObject.getString("result"));
                    }
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                })
        );
    }

    MutableLiveData<List<DataAddressResponse>> getLiveCartAddrDataList() {
        return cartData;
    }

    public ObservableList<DataAddressResponse> getCartDataList() {
        return cartDataList;
    }

    void setDataToCartAddrList(List<DataAddressResponse> dataListApi){
        cartDataList.clear();
        cartDataList.addAll(dataListApi);
    }

    private void setAddressModel(JSONObject jsonObject){
        try{
            JSONArray arrResult = jsonObject.getJSONArray("result");
            int arrResultSize = arrResult.length();
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
            cartData.setValue(addresslist);
        } catch (Exception e){
            Log.e("errJSONProd tes1", e.getMessage()+"");
            getNavigator().handleError(e.fillInStackTrace());
        }
    }

}
