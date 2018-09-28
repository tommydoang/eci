package com.example.tomz.electroniccity.page.side_menu.policy;

import android.databinding.ObservableField;
import android.os.Build;
import android.text.Html;

import com.example.tomz.electroniccity.data.model.api.policy.DataPolicyResponse;

public class PolicyItemViewModel {

    public final ObservableField<String> descPolicy;

    public PolicyItemViewModel(DataPolicyResponse dataPolicyResponse){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            descPolicy = new ObservableField<>(String.valueOf(Html
                    .fromHtml(dataPolicyResponse.getDescription(), Html.FROM_HTML_MODE_LEGACY)));
        } else {
            descPolicy = new ObservableField<>(String.valueOf(Html.fromHtml(dataPolicyResponse.getDescription())));
        }
    }

}
