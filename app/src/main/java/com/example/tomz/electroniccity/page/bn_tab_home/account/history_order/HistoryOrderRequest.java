package com.example.tomz.electroniccity.page.bn_tab_home.account.history_order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryOrderRequest {

    public HistoryOrderRequest() { }

    public static class req{
        @Expose
        @SerializedName("id_customer")
        private String userId;

        public req (String userId) { this. userId = userId; }

        public String getUserId() { return userId; }
    }
}
