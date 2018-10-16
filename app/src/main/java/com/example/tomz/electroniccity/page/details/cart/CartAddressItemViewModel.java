package com.example.tomz.electroniccity.page.details.cart;

import android.databinding.ObservableField;

import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;

public class CartAddressItemViewModel {

    public final ObservableField<String> nameReceiver;
    public final ObservableField<String> addressName;
    public final ObservableField<String> phoneNumber;

    public CartAddressItemViewModel(DataAddressResponse dataModelAddress) {
        nameReceiver = new ObservableField<>(dataModelAddress.getNameReceiver());
        addressName = new ObservableField<>(dataModelAddress.getAddress());
        phoneNumber = new ObservableField<>(dataModelAddress.getPhoneNumber1());
    }

}
