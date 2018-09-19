package com.example.tomz.electroniccity.data.local.pref;

public interface PreferencesHelper {

    int getIntroFlag();
    void setIntroFlag(int flagIntro);

    String getAuthToken();
    void setAuthToken(String authToken);

    String getUserId();
    void setUserId(String userId);
    String getUserName();
    void setUserName(String userName);
    String getUserKTP();
    void setUserKTP(String userKTP);
    String getUserEmail();
    void setUserEmail(String email);
    String getUserFullName();
    void setUserFullName(String userFullName);
    String getUserBirthday();
    void setUserBirthday(String userBirthday);
    String getUserPhoneNumber();
    void setUserPhoneNumber(String phoneNumber);
    String getUserHP();
    void setUserHP(String hp);
    String getUserAddress1();
    void setUserAddress1(String address1);
    String getUserAddress2();
    void setUserAddress2(String address2);
    String getUserAddress3();
    void setUserAddress3(String address3);
    String getUserAddress4();
    void setUserAddress4(String address4);
    String getUserDistrictId();
    void setUserDistrictId(String districtId);
    String getUserZipCode();
    void setUserZipCode(String zipCode);
    String getUserRuleID();
    void setUserRuleID(String ruleID);
    String getUserType();
    void setUserType(String userType);
    String getUserRate();
    void setUserRate(String userRate);
    String getUserFacebook();
    void setUserFacebook(String userFacebook);
    String getUserMemberID();
    void setUserMemberID(String memberID);
    String getUserActivateDate();
    void setUserActivateDate(String activateDate);
    String getUserChannel();
    void setUserChannel(String channel);

    String getUserLat();
    void setUserLat(String userLat);
    String getUserLng();
    void setUserLng (String userLng);

    int getIntFlag();
    void setIntFlag(int flag);
    String getStrFlag();
    void setStrFlag(String flag);

    int getUserPointReward();
    void setUserPointReward(int point);

    String getLastAuthDate();
    void setLastAuthDate(String authDate);
    String getNewAuthDate();
    void setNewAuthDate(String authDate);

    String getDeviceId();
    void setDeviceId(String deviceId);

    void removeAllPreferences();

}
