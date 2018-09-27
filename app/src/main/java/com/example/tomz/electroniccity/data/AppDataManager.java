package com.example.tomz.electroniccity.data;

import android.content.Context;

import com.example.tomz.electroniccity.data.local.db.DbHelper;
import com.example.tomz.electroniccity.data.local.pref.PreferencesHelper;
import com.example.tomz.electroniccity.data.model.db.CartMdl;
import com.example.tomz.electroniccity.data.model.db.WishesMdl;
import com.example.tomz.electroniccity.data.remote.ApiHelper;
import com.example.tomz.electroniccity.page.bn_tab_home.account.AuthRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.ForgetPassRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.address.AllAddressRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.history_order.HistoryOrderRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.invite.InviteMemberRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.login.LoginRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.register_forget.RegisterRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;
    private final Context mContext;
    private final DbHelper mDbHelper;
    private final Gson mGson;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context ctx, DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper, Gson gson) {
        mContext = ctx;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    /**
     * for database
     */
    @Override
    public Observable<List<CartMdl>> getAllCart() {
        return mDbHelper.getAllCart();
    }

    @Override
    public Observable<List<WishesMdl>> getAllWishes() {
        return mDbHelper.getAllWishes();
    }

    @Override
    public Observable<Boolean> insertCart(CartMdl cart) {
        return mDbHelper.insertCart(cart);
    }

    @Override
    public Observable<Boolean> insertWish(WishesMdl wish) {
        return mDbHelper.insertWish(wish);
    }

    /**
     * for preferences
     */

    @Override
    public int getIntroFlag() {
        return mPreferencesHelper.getIntroFlag();
    }

    @Override
    public void setIntroFlag(int flagIntro) {
        mPreferencesHelper.setIntroFlag(flagIntro);
    }

    @Override
    public String getAuthToken() {
        return mPreferencesHelper.getAuthToken();
    }

    @Override
    public void setAuthToken(String authToken) {
        mPreferencesHelper.setAuthToken(authToken);
    }

    @Override
    public String getUserId() {
        return mPreferencesHelper.getUserId();
    }

    @Override
    public void setUserId(String userId) {
        mPreferencesHelper.setUserId(userId);
    }

    @Override
    public String getUserName() {
        return mPreferencesHelper.getUserName();
    }

    @Override
    public void setUserName(String userName) {
        mPreferencesHelper.setUserName(userName);
    }

    @Override
    public String getUserKTP() {
        return mPreferencesHelper.getUserKTP();
    }

    @Override
    public void setUserKTP(String userKTP) {
        mPreferencesHelper.setUserKTP(userKTP);
    }

    @Override
    public String getUserEmail() {
        return mPreferencesHelper.getUserEmail();
    }

    @Override
    public void setUserEmail(String email) {
        mPreferencesHelper.setUserEmail(email);
    }

    @Override
    public String getUserFullName() {
        return mPreferencesHelper.getUserFullName();
    }

    @Override
    public void setUserFullName(String userFullName) {
        mPreferencesHelper.setUserFullName(userFullName);
    }

    @Override
    public String getUserBirthday() {
        return mPreferencesHelper.getUserBirthday();
    }

    @Override
    public void setUserBirthday(String userBirthday) {
        mPreferencesHelper.setUserBirthday(userBirthday);
    }

    @Override
    public String getUserPhoneNumber() {
        return mPreferencesHelper.getUserPhoneNumber();
    }

    @Override
    public void setUserPhoneNumber(String phoneNumber) {
        mPreferencesHelper.setUserPhoneNumber(phoneNumber);
    }

    @Override
    public String getUserHP() {
        return mPreferencesHelper.getUserHP();
    }

    @Override
    public void setUserHP(String hp) {
        mPreferencesHelper.setUserHP(hp);
    }

    @Override
    public String getUserAddress1() {
        return mPreferencesHelper.getUserAddress1();
    }

    @Override
    public void setUserAddress1(String address1) {
        mPreferencesHelper.setUserAddress1(address1);
    }

    @Override
    public String getUserAddress2() {
        return mPreferencesHelper.getUserAddress2();
    }

    @Override
    public void setUserAddress2(String address2) {
        mPreferencesHelper.setUserAddress2(address2);
    }

    @Override
    public String getUserAddress3() {
        return mPreferencesHelper.getUserAddress3();
    }

    @Override
    public void setUserAddress3(String address3) {
        mPreferencesHelper.setUserAddress3(address3);
    }

    @Override
    public String getUserAddress4() {
        return mPreferencesHelper.getUserAddress4();
    }

    @Override
    public void setUserAddress4(String address4) {
        mPreferencesHelper.setUserAddress4(address4);
    }

    @Override
    public String getUserDistrictId() {
        return mPreferencesHelper.getUserDistrictId();
    }

    @Override
    public void setUserDistrictId(String districtId) {
        mPreferencesHelper.setUserDistrictId(districtId);
    }

    @Override
    public String getUserZipCode() {
        return mPreferencesHelper.getUserZipCode();
    }

    @Override
    public void setUserZipCode(String zipCode) {
        mPreferencesHelper.setUserZipCode(zipCode);
    }

    @Override
    public String getUserRuleID() {
        return mPreferencesHelper.getUserRuleID();
    }

    @Override
    public void setUserRuleID(String ruleID) {
        mPreferencesHelper.setUserRuleID(ruleID);
    }

    @Override
    public String getUserType() {
        return mPreferencesHelper.getUserType();
    }

    @Override
    public void setUserType(String userType) {
        mPreferencesHelper.setUserType(userType);
    }

    @Override
    public String getUserRate() {
        return mPreferencesHelper.getUserRate();
    }

    @Override
    public void setUserRate(String userRate) {
        mPreferencesHelper.setUserRate(userRate);
    }

    @Override
    public String getUserFacebook() {
        return mPreferencesHelper.getUserFacebook();
    }

    @Override
    public void setUserFacebook(String userFacebook) {
        mPreferencesHelper.setUserFacebook(userFacebook);
    }

    @Override
    public String getUserMemberID() {
        return mPreferencesHelper.getUserMemberID();
    }

    @Override
    public void setUserMemberID(String memberID) {
        mPreferencesHelper.setUserMemberID(memberID);
    }

    @Override
    public String getUserActivateDate() {
        return mPreferencesHelper.getUserActivateDate();
    }

    @Override
    public void setUserActivateDate(String activateDate) {
        mPreferencesHelper.setUserActivateDate(activateDate);
    }

    @Override
    public String getUserChannel() {
        return mPreferencesHelper.getUserChannel();
    }

    @Override
    public void setUserChannel(String channel) {
        mPreferencesHelper.setUserChannel(channel);
    }

    @Override
    public String getUserLat() {
        return mPreferencesHelper.getUserLat();
    }

    @Override
    public void setUserLat(String userLat) {
        mPreferencesHelper.setUserLat(userLat);
    }

    @Override
    public String getUserLng() {
        return mPreferencesHelper.getUserLng();
    }

    @Override
    public void setUserLng(String userLng) {
        mPreferencesHelper.setUserLng(userLng);
    }

    @Override
    public int getIntFlag() {
        return mPreferencesHelper.getIntFlag();
    }

    @Override
    public void setIntFlag(int flag) {
        mPreferencesHelper.setIntFlag(flag);
    }

    @Override
    public String getStrFlag() {
        return mPreferencesHelper.getStrFlag();
    }

    @Override
    public void setStrFlag(String flag) {
        mPreferencesHelper.setStrFlag(flag);
    }

    @Override
    public int getUserPointReward() {
        return mPreferencesHelper.getUserPointReward();
    }

    @Override
    public void setUserPointReward(int point) {
        mPreferencesHelper.setUserPointReward(point);
    }

    @Override
    public String getLastAuthDate() {
        return mPreferencesHelper.getLastAuthDate();
    }

    @Override
    public void setLastAuthDate(String authDate) {
        mPreferencesHelper.setLastAuthDate(authDate);
    }

    @Override
    public String getNewAuthDate() {
        return mPreferencesHelper.getNewAuthDate();
    }

    @Override
    public void setNewAuthDate(String authDate) {
        mPreferencesHelper.setNewAuthDate(authDate);
    }

    @Override
    public String getDeviceId() {
        return mPreferencesHelper.getDeviceId();
    }

    @Override
    public void setDeviceId(String deviceId) {
        mPreferencesHelper.setDeviceId(deviceId);
    }

    @Override
    public void removeAllPreferences() {
        mPreferencesHelper.removeAllPreferences();
    }

    /**
     * for Api
     */
    @Override
    public Observable<JSONObject> doAuthApiCall(AuthRequest.req request) {
        return mApiHelper.doAuthApiCall(request);
    }

    @Override
    public Observable<JSONObject> doLoginApiCall(LoginRequest.req request) {
        return mApiHelper.doLoginApiCall(request);
    }

    @Override
    public Observable<JSONObject> doRegisterApiCall(RegisterRequest.req request) {
        return mApiHelper.doRegisterApiCall(request);
    }

    @Override
    public Observable<JSONObject> doInviteMemberApiCall(InviteMemberRequest.req request) {
        return mApiHelper.doInviteMemberApiCall(request);
    }

    @Override
    public Observable<JSONObject> doForgetPassApiCall(ForgetPassRequest.req request) {
        return mApiHelper.doForgetPassApiCall(request);
    }

    @Override
    public Observable<JSONArray> doAllProductsApiCall() {
        return mApiHelper.doAllProductsApiCall();
    }

    @Override
    public Observable<JSONObject> doGetBannerApiCall() {
        return mApiHelper.doGetBannerApiCall();
    }

    @Override
    public Observable<JSONObject> doGetDealApiCall() {
        return mApiHelper.doGetDealApiCall();
    }

    @Override
    public Observable<JSONObject> doGetAllAddressApiCall(AllAddressRequest.req request) {
        return mApiHelper.doGetAllAddressApiCall(request);
    }

    @Override
    public Observable<JSONObject> doGetAllHistoryOrderApiCall(HistoryOrderRequest.req request) {
        return mApiHelper.doGetAllHistoryOrderApiCall(request);
    }

    @Override
    public Observable<JSONObject> doGetAllValueAddedApiCall() {
        return mApiHelper.doGetAllValueAddedApiCall();
    }

    @Override
    public Observable<JSONObject> doGetAboutUsApiCall() {
        return mApiHelper.doGetAboutUsApiCall();
    }

    /**
     *  DataModel
     */
    @Override
    public void authInfo(String deviceId, String authKey) {
        setDeviceId(deviceId);
        setAuthToken(authKey);
    }

    @Override
    public void userInfo(String userID, String userName,
                         String userKTP, String userEmail, String userFullName, String userBirthday,
                         String userPhone, String userHandphone, String userAddress1,
                         String userAddress2, String userAddress3, String userAddress4,
                         String userDistrictID, String userZipCode, String userRuleID,
                         String userTypeID, String userRate, String userMemberID,
                         String userActivateDate, String userChannel) {

        setUserId(userID);
        setUserName(userName);
        setUserKTP(userKTP);
        setUserEmail(userEmail);
        setUserFullName(userFullName);
        setUserBirthday(userBirthday);
        setUserPhoneNumber(userPhone);
        setUserHP(userHandphone);
        setUserAddress1(userAddress1);
        setUserAddress2(userAddress2);
        setUserAddress3(userAddress3);
        setUserAddress4(userAddress4);
        setUserDistrictId(userDistrictID);
        setUserZipCode(userZipCode);
        setUserRuleID(userRuleID);
        setUserType(userTypeID);
        setUserRate(userRate);
        setUserMemberID(userMemberID);
        setUserActivateDate(userActivateDate);
        setUserChannel(userChannel);
    }

//    @Override
//    public void allProducts(String id_category_head, String id_prod,
//                            String sku, String name_prod, String tags,
//                            String model, String size, String id_brand,
//                            String id_cat, String id_cat_new, String product_description,
//                            String long_description, String real_price, String spc_price,
//                            String video_link, String isPublish, String userCreated,
//                            String dateCreated, String userModified, String dateModified,
//                            String publisher, String publisher_time, String publisher_user,
//                            String type_prod, String delivery_type, String city_free_shipping,
//                            String city_free_shipping_all, String viewed, String turun_harga,
//                            String naik_harga, String img_best, String img_thumb,
//                            String merk_name_brand, String attr_id_prod, String stock_store_code,
//                            String number_stock, String stock_item_bought) {
//
//
//
//
//    }

}
