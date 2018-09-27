package com.example.tomz.electroniccity.page.bn_tab_home.account.register_forget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    public RegisterRequest() {
        // This class is not publicly instantiable
    }

    public static class req {
        @Expose
        @SerializedName("Customer[fullname]")
        private String userFullName;

        @Expose
        @SerializedName("Customer[handphone]")
        private String userHP;

        public req(String userFullName, String userHP) {
            this.userFullName = userFullName;
            this.userHP = userHP;
        }

        public String getUserFullName() {
            return userFullName;
        }

        public String getUserHP() {
            return userHP;
        }
    }

}
