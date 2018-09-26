package com.example.tomz.electroniccity.page.side_menu.value;

import com.example.tomz.electroniccity.data.model.api.valueadded.DataValueAddResponse;

import java.util.List;

public interface ValueAddNavigator {

    void onSuccess(List<DataValueAddResponse> dataValueAddResponseList);
    void onFailed(String message);
    void handleError(Throwable throwable);
    void updateList(List<DataValueAddResponse> dataValueAddResponseList);

}
