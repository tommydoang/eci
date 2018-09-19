package com.example.tomz.electroniccity.data.model.api.membership.__login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataLoginResponse {

    @Expose
    @SerializedName("status") private String statusResponse;
    @Expose
    @SerializedName("id_customer") private String userID;
    @Expose
    @SerializedName("username") private String userName;
    @Expose
    @SerializedName("identityID") private String userKTP;
    @Expose
    @SerializedName("email") private String userEmail;
    @Expose
    @SerializedName("fullname") private String userFullName;
    @Expose
    @SerializedName("phone_number") private String userPhone;
    @Expose
    @SerializedName("handphone") private String userHP;
    @Expose
    @SerializedName("address") private String userAddress1;
    @Expose
    @SerializedName("address2") private String userAddress2;
    @Expose
    @SerializedName("address3") private String userAddress3;
    @Expose
    @SerializedName("address4") private String userAddress4;
    @Expose
    @SerializedName("id_districts") private String userDistricts;
    @Expose
    @SerializedName("kode_pos") private String userZipCode;
    @Expose
    @SerializedName("isActive") private String isUserActive;
    @Expose
    @SerializedName("id_rule") private String userRuleId;
    @Expose
    @SerializedName("id_type_customer") private String userTypeId;
    @Expose
    @SerializedName("id_reputation") private String userRating;
    @Expose
    @SerializedName("id_facebook") private String userFacebook;
    @Expose
    @SerializedName("sap_code") private String userMemberID;
    @Expose
    @SerializedName("activation_date") private String memberActivateDate;
    @Expose
    @SerializedName("channel") private String userChannel;
    @Expose
    @SerializedName("password") private String[] errLogin;

    public String getUserKTP() {
        return userKTP;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserZipCode() {
        return userZipCode;
    }

    public String getUserFacebook() {
        return userFacebook;
    }

    public String getUserChannel() {
        return userChannel;
    }

    public String getUserRuleId() {
        return userRuleId;
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public String getUserRating() {
        return userRating;
    }

    public String getMemberActivateDate() {
        return memberActivateDate;
    }

    public String getStatusResponse() {
        return statusResponse;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public String getUserHP() {
        return userHP;
    }

    public String getUserAddress1() {
        return userAddress1;
    }

    public String getUserAddress2() {
        return userAddress2;
    }

    public String getUserAddress3() {
        return userAddress3;
    }

    public String getUserAddress4() {
        return userAddress4;
    }

    public String getUserDistricts() {
        return userDistricts;
    }

    public String getIsUserActive() {
        return isUserActive;
    }

    public String getUserMemberID() {
        return userMemberID;
    }

    public String[] getErrLogin() {
        return errLogin;
    }

}
