package com.example.tomz.electroniccity.data.model.api.membership.__register;

import com.google.gson.annotations.SerializedName;

public class DataRegisterResponse {
    @SerializedName("status") private String statusResponse;
    @SerializedName("sms_send") private int smsSend;
    @SerializedName("email_send") private int emailSend;
    @SerializedName("email") private String regEmail;
    @SerializedName("fullname") private String regFullName;
    @SerializedName("handphone") private String regHP;
    @SerializedName("username") private String regUserName;
    @SerializedName("password") private String regPassword;
    @SerializedName("registered_by") private String registerBy;
    @SerializedName("birthday") private String regBirthday;
    @SerializedName("activation_key") private String regActiveKey;
    @SerializedName("dateCreated") private String regDateCreate;
    @SerializedName("userCreated") private String regUserCreated;
    @SerializedName("id_customer") private String regCustomerID;
    @SerializedName("identityID") private String regIdentityID;
    @SerializedName("gender") private String regGender;
    @SerializedName("phone_number") private String regPhoneNumber;
    @SerializedName("address") private String regAddress1;
    @SerializedName("address2") private String regAddress2;
    @SerializedName("address3") private String regAddress3;
    @SerializedName("id_districts") private String regDistrictID;
    @SerializedName("kode_pos") private String regKodePos;
    @SerializedName("message") private String regMessage;

    public String getStatusResponse() {
        return statusResponse;
    }

    public int getSmsSend() {
        return smsSend;
    }

    public int getEmailSend() {
        return emailSend;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public String getRegFullName() {
        return regFullName;
    }

    public String getRegHP() {
        return regHP;
    }

    public String getRegUserName() {
        return regUserName;
    }

    public String getRegPassword() {
        return regPassword;
    }

    public String getRegisterBy() {
        return registerBy;
    }

    public String getRegBirthday() {
        return regBirthday;
    }

    public String getRegActiveKey() {
        return regActiveKey;
    }

    public String getRegDateCreate() {
        return regDateCreate;
    }

    public String getRegUserCreated() {
        return regUserCreated;
    }

    public String getRegCustomerID() {
        return regCustomerID;
    }

    public String getRegIdentityID() {
        return regIdentityID;
    }

    public String getRegGender() {
        return regGender;
    }

    public String getRegPhoneNumber() {
        return regPhoneNumber;
    }

    public String getRegAddress1() {
        return regAddress1;
    }

    public String getRegAddress2() {
        return regAddress2;
    }

    public String getRegAddress3() {
        return regAddress3;
    }

    public String getRegDistrictID() {
        return regDistrictID;
    }

    public String getRegKodePos() {
        return regKodePos;
    }

    public String getRegMessage() {
        return regMessage;
    }
}
