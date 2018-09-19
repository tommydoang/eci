package com.example.tomz.electroniccity.page.bn_tab_home.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPassRequest {

    public ForgetPassRequest() { }

    public static class req {
        @Expose
        @SerializedName("email")
        private String userEmail;

        public req(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getUserEmail() {
            return userEmail;
        }
    }
}
