package com.example.tomz.electroniccity.page.side_menu.policy;

import android.databinding.ObservableField;

import com.example.tomz.electroniccity.data.model.api.policy.DataPolicyResponse;

public class PolicyItemViewModel {

    public final ObservableField<String> descPolicy;

    public PolicyItemViewModel(DataPolicyResponse dataPolicyResponse){
        descPolicy = new ObservableField<>(dataPolicyResponse.getDescription());
    }

}
