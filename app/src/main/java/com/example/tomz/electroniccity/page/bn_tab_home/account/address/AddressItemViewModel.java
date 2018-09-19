package com.example.tomz.electroniccity.page.bn_tab_home.account.address;

import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;

public class AddressItemViewModel {

    public final ObservableField<String> titleAddress;
    public final ObservableField<String> nameReceiver;
    public final ObservableField<String> addressName;
    public final ObservableField<String> phoneNumber;

    public AddressItemViewModel(DataAddressResponse dataModelAddress) {
        titleAddress = new ObservableField<>(dataModelAddress.getTitleAddress());
        nameReceiver = new ObservableField<>(dataModelAddress.getNameReceiver());
        addressName = new ObservableField<>(dataModelAddress.getAddress());
        phoneNumber = new ObservableField<>(dataModelAddress.getPhoneNumber1());
    }

    public View.OnClickListener onReadMoreClicked() {
        return view -> Toast.makeText(view.getContext(), nameReceiver.get(), Toast.LENGTH_SHORT).show();
    }

    public View.OnClickListener onUbahClicked() {
        return view -> Toast.makeText(view.getContext(), "UBAH!!!", Toast.LENGTH_SHORT).show();
    }

    public View.OnClickListener onHapusClicked() {
        return view -> Toast.makeText(view.getContext(), "HAPUSS!!!", Toast.LENGTH_SHORT).show();
    }

}
