package com.example.tomz.electroniccity.page.bn_tab_home.account.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginRequest {

    private LoginRequest(){
        // This class is not publicly instantiable
    }

    public static class req {
        @Expose
        @SerializedName("LoginForm[username]")
        private String username;

        @Expose
        @SerializedName("LoginForm[password]")
        private String password;

        public req(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

}
