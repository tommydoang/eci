package com.example.tomz.electroniccity.page.bn_tab_home.account.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllAddressRequest {

    public AllAddressRequest() { }

    public static class req {
        @Expose
        @SerializedName("id_custommer")
        private String userAddress;

        public req(String userAddress) {
            this.userAddress = userAddress;
        }

        public String getUserAddress() {
            return userAddress;
        }

    }

}
