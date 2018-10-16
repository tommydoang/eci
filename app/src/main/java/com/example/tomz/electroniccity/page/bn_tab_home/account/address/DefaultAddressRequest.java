package com.example.tomz.electroniccity.page.bn_tab_home.account.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultAddressRequest {

    public DefaultAddressRequest() { }

    public static class req{
        @Expose
        @SerializedName("id_custommer")
        private String userAddress;

        @Expose
        @SerializedName("isDefault")
        private String isDefault;

        public req(String userAddress, String isDefault) {
            this.userAddress = userAddress;
            this.isDefault = isDefault;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public String getIsDefault() {
            return isDefault;
        }
    }
}
