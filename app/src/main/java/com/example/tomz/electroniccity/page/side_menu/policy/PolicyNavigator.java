package com.example.tomz.electroniccity.page.side_menu.policy;

import com.example.tomz.electroniccity.data.model.api.policy.DataPolicyResponse;

import java.util.List;

public interface PolicyNavigator {

    void onSuccess();
    void onFailed();
    void handleError(Throwable throwable);
    void updateList(List<DataPolicyResponse> dataPolicyResponseList);
}
