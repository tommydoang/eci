package com.example.tomz.electroniccity.page.bn_tab_home.account.invite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InviteMemberRequest {

    public InviteMemberRequest() {
        // This class is not publicly instantiable
    }

    public static class req {
        @Expose
        @SerializedName("Customer[handphone]")
        private String userHandphone;

        public req(String handphone){
            this.userHandphone = handphone;
        }

        public String getUserHandphone() {
            return userHandphone;
        }
    }

}
