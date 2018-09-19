package com.example.tomz.electroniccity.data.local.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.tomz.electroniccity.di.PreferencesInfo;

import javax.inject.Inject;

import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_INTRO_FLAG;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_LAST_AUTH_DATE;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_NEW_AUTH_DATE;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_ACTIVATE_DATE;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_ADDR1;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_ADDR2;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_ADDR3;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_ADDR4;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_BIRTHDAY;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_CHANNEL;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_DEVICE_ID;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_DISTRICT;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_EMAIL;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_FACEBOOK;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_FULL_NAME;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_HP;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_ID;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_KTP;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_LAT;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_LNG;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_MEMBER_ID;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_NAME;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_PHONE;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_POINT;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_RATE;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_RULE_ID;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_TOKEN;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_INT_FLAG;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_STR_FLAG;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_TYPE;
import static com.example.tomz.electroniccity.utils.AppConstants.PREF_KEY_USER_ZIP_CODE;

public class AppPreferencesHelper implements PreferencesHelper{

    private final SharedPreferences mSharedPreferences;

    @Inject
    public AppPreferencesHelper(Context ctx, @PreferencesInfo String prefName){
        mSharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    @Override
    public int getIntroFlag() {
        return mSharedPreferences.getInt(PREF_KEY_INTRO_FLAG, 0);
    }

    @Override
    public void setIntroFlag(int flagIntro) {
        mSharedPreferences.edit().putInt(PREF_KEY_INTRO_FLAG, flagIntro).apply();
    }

    @Override
    public String getAuthToken() {
        return mSharedPreferences.getString(PREF_KEY_USER_TOKEN,"");
    }

    @Override
    public void setAuthToken(String authToken) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_TOKEN, authToken).apply();
    }

    @Override
    public String getUserEmail() {
        return mSharedPreferences.getString(PREF_KEY_USER_EMAIL,"");
    }

    @Override
    public void setUserEmail(String email) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_EMAIL, email).apply();
    }

    @Override
    public String getUserFullName() {
        return mSharedPreferences.getString(PREF_KEY_USER_FULL_NAME,"");
    }

    @Override
    public void setUserFullName(String userFullName) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_FULL_NAME, userFullName).apply();
    }

    @Override
    public String getUserBirthday() {
        return mSharedPreferences.getString(PREF_KEY_USER_BIRTHDAY,"");
    }

    @Override
    public void setUserBirthday(String userBirthday) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_BIRTHDAY, userBirthday).apply();
    }

    @Override
    public String getUserPhoneNumber() {
        return mSharedPreferences.getString(PREF_KEY_USER_PHONE, "");
    }

    @Override
    public void setUserPhoneNumber(String phoneNumber) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_PHONE, phoneNumber).apply();
    }

    @Override
    public String getUserHP() {
        return mSharedPreferences.getString(PREF_KEY_USER_HP, "");
    }

    @Override
    public void setUserHP(String hp) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_HP, hp).apply();
    }

    @Override
    public String getUserAddress1() {
        return mSharedPreferences.getString(PREF_KEY_USER_ADDR1, "");
    }

    @Override
    public void setUserAddress1(String address1) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_ADDR1, address1).apply();
    }

    @Override
    public String getUserAddress2() {
        return mSharedPreferences.getString(PREF_KEY_USER_ADDR2, "");
    }

    @Override
    public void setUserAddress2(String address2) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_ADDR2, address2).apply();
    }

    @Override
    public String getUserAddress3() {
        return mSharedPreferences.getString(PREF_KEY_USER_ADDR3, "");
    }

    @Override
    public void setUserAddress3(String address3) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_ADDR3, address3).apply();
    }

    @Override
    public String getUserAddress4() {
        return mSharedPreferences.getString(PREF_KEY_USER_ADDR4, "");
    }

    @Override
    public void setUserAddress4(String address4) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_ADDR4, address4).apply();
    }

    @Override
    public String getUserDistrictId() {
        return mSharedPreferences.getString(PREF_KEY_USER_DISTRICT, "");
    }

    @Override
    public void setUserDistrictId(String districtId) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_DISTRICT, districtId).apply();
    }

    @Override
    public String getUserZipCode() {
        return mSharedPreferences.getString(PREF_KEY_USER_ZIP_CODE,"");
    }

    @Override
    public void setUserZipCode(String zipCode) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_ZIP_CODE, zipCode).apply();
    }

    @Override
    public String getUserRuleID() {
        return mSharedPreferences.getString(PREF_KEY_USER_RULE_ID, "");
    }

    @Override
    public void setUserRuleID(String ruleID) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_RULE_ID, ruleID).apply();
    }

    @Override
    public String getUserType() {
        return mSharedPreferences.getString(PREF_KEY_USER_TYPE, "");
    }

    @Override
    public void setUserType(String userType) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_TYPE, userType).apply();
    }

    @Override
    public String getUserRate() {
        return mSharedPreferences.getString(PREF_KEY_USER_RATE, "");
    }

    @Override
    public void setUserRate(String userRate) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_RATE, userRate).apply();
    }

    @Override
    public String getUserFacebook() {
        return mSharedPreferences.getString(PREF_KEY_USER_FACEBOOK, "");
    }

    @Override
    public void setUserFacebook(String userFacebook) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_FACEBOOK, userFacebook).apply();
    }

    @Override
    public String getUserMemberID() {
        return mSharedPreferences.getString(PREF_KEY_USER_MEMBER_ID, "");
    }

    @Override
    public void setUserMemberID(String memberID) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_MEMBER_ID, memberID).apply();
    }

    @Override
    public String getUserActivateDate() {
        return mSharedPreferences.getString(PREF_KEY_USER_ACTIVATE_DATE, "");
    }

    @Override
    public void setUserActivateDate(String activateDate) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_ACTIVATE_DATE, activateDate).apply();
    }

    @Override
    public String getUserChannel() {
        return mSharedPreferences.getString(PREF_KEY_USER_CHANNEL, "");
    }

    @Override
    public void setUserChannel(String channel) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_CHANNEL, channel).apply();
    }

    @Override
    public String getUserId() {
        return mSharedPreferences.getString(PREF_KEY_USER_ID,"");
    }

    @Override
    public void setUserId(String userId) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_ID, userId).apply();
    }

    @Override
    public String getUserName() {
        return mSharedPreferences.getString(PREF_KEY_USER_NAME,"");
    }

    @Override
    public void setUserName(String userName) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_NAME, userName).apply();
    }

    @Override
    public String getUserKTP() {
        return mSharedPreferences.getString(PREF_KEY_USER_KTP, "");
    }

    @Override
    public void setUserKTP(String userKTP) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_KTP, userKTP).apply();
    }

    @Override
    public String getUserLat() {
        return mSharedPreferences.getString(PREF_KEY_USER_LAT,"");
    }

    @Override
    public void setUserLat(String userLat) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_LAT, userLat).apply();
    }

    @Override
    public String getUserLng() {
        return mSharedPreferences.getString(PREF_KEY_USER_LNG, "");
    }

    @Override
    public void setUserLng(String userLng) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_LNG, userLng).apply();
    }

    /**
     * ini helper
     */
    @Override
    public int getIntFlag() {
        return mSharedPreferences.getInt(PREF_KEY_INT_FLAG, 0);
    }

    @Override
    public void setIntFlag(int flag) {
        mSharedPreferences.edit().putInt(PREF_KEY_INT_FLAG, flag).apply();
    }

    @Override
    public String getStrFlag() {
        return mSharedPreferences.getString(PREF_KEY_STR_FLAG, "");
    }

    @Override
    public void setStrFlag(String flag) {
        mSharedPreferences.edit().putString(PREF_KEY_STR_FLAG, flag).apply();
    }

    /**
     * ini belum dipakai
     */
    @Override
    public int getUserPointReward() {
        return mSharedPreferences.getInt(PREF_KEY_USER_POINT, 0);
    }

    @Override
    public void setUserPointReward(int point) {
        mSharedPreferences.edit().putInt(PREF_KEY_USER_POINT, point).apply();
    }
    /**
     * akhir yg belum dipakai
     */

    @Override
    public String getLastAuthDate() {
        return mSharedPreferences.getString(PREF_KEY_LAST_AUTH_DATE, "");
    }

    @Override
    public void setLastAuthDate(String authDate) {
        mSharedPreferences.edit().putString(PREF_KEY_LAST_AUTH_DATE, authDate).apply();
    }

    @Override
    public String getNewAuthDate() {
        return mSharedPreferences.getString(PREF_KEY_NEW_AUTH_DATE, "");
    }

    @Override
    public void setNewAuthDate(String authDate) {
        mSharedPreferences.edit().putString(PREF_KEY_NEW_AUTH_DATE, authDate).apply();
    }

    @Override
    public String getDeviceId() {
        return mSharedPreferences.getString(PREF_KEY_USER_DEVICE_ID, "");
    }

    @Override
    public void setDeviceId(String deviceId) {
        mSharedPreferences.edit().putString(PREF_KEY_USER_DEVICE_ID, deviceId).apply();
    }

    @Override
    public void removeAllPreferences() {
        mSharedPreferences.edit().remove(PREF_KEY_USER_ID).apply();
    }

}
