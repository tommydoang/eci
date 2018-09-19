package com.example.tomz.electroniccity.data.model._testdoang;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tomz on 2/3/2018.
 */

public class DataIdeasResponse {
    @SerializedName("status")
    private String statusResponse;
    @SerializedName("message")
    private List<DataModelIdeas> dataModelIdeasList;

    public String getStatusResponse() {
        return statusResponse;
    }

    public List<DataModelIdeas> getDataModelIdeasList() {
        return dataModelIdeasList;
    }
}
