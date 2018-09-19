package com.example.tomz.electroniccity.page.bn_tab_home.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthRequest {

    public AuthRequest() {
        // This class is not publicly instantiable
    }

    public static class req {
        @Expose
        @SerializedName("credit")
        private String credit;

        public req(String credit){
            this.credit = credit;
        }

        public String getCreditId() { return credit; }

    }

}
