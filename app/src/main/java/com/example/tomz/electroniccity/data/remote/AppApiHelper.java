package com.example.tomz.electroniccity.data.remote;

import android.util.Log;

import com.androidnetworking.common.Priority;
import com.example.tomz.electroniccity.data.local.pref.PreferencesHelper;
import com.example.tomz.electroniccity.page.bn_tab_home.account.AuthRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.ForgetPassRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.address.AllAddressRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.history_order.HistoryOrderRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.invite.InviteMemberRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.login.LoginRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.register_forget.RegisterRequest;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
//import retrofit2.Call;

@Singleton
public class AppApiHelper implements ApiHelper {

    private PreferencesHelper mPrefHelper;

    @Inject
    public AppApiHelper(PreferencesHelper mPreferencesHelper) {
        this.mPrefHelper = mPreferencesHelper;
    }

    @Override
    public Observable<JSONObject> doAuthApiCall(AuthRequest.req request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_AUTH)
                .addBodyParameter(request)
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable();
    }

    @Override
    public Observable<JSONObject> doLoginApiCall(LoginRequest.req request) {
        Log.d("AppApi tes1", mPrefHelper.getAuthToken());
         return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN)
                 .addHeaders("Authorization", mPrefHelper.getAuthToken())
                 .addBodyParameter(request)
                 .setPriority(Priority.MEDIUM)
                 .build()
                 .getJSONObjectObservable();
    }

    @Override
    public Observable<JSONObject> doRegisterApiCall(RegisterRequest.req request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_REGISTER)
                .addHeaders("Authorization",mPrefHelper.getAuthToken())
                .addBodyParameter(request)
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable();
    }

    @Override
    public Observable<JSONObject> doInviteMemberApiCall(InviteMemberRequest.req request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_INVITE_MEMBER)
                .addHeaders("Authorization", mPrefHelper.getAuthToken())
                .addBodyParameter(request)
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable();
    }

    @Override
    public Observable<JSONObject> doForgetPassApiCall(ForgetPassRequest.req request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FORGET_PASSWORD)
                .addHeaders("Authorization", mPrefHelper.getAuthToken())
                .addBodyParameter(request)
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable();
    }

    @Override
    public Observable<JSONArray> doAllProductsApiCall() {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_ALL_PRODUCTS)
                .addHeaders("Authorization", mPrefHelper.getAuthToken())
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONArrayObservable();
    }

    @Override
    public Observable<JSONObject> doGetBannerApiCall() {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GET_BANNER)
                .addHeaders("Authorization", mPrefHelper.getAuthToken())
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable();
    }

    @Override
    public Observable<JSONObject> doGetDealApiCall() {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GET_SUPER_DEAL)
                .addHeaders("Authorization", mPrefHelper.getAuthToken())
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable();
    }

    @Override
    public Observable<JSONObject> doGetAllAddressApiCall(AllAddressRequest.req request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GET_ALL_ADDRESS)
                .addHeaders("Authorization", mPrefHelper.getAuthToken())
                .addBodyParameter(request)
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable();
    }

    @Override
    public Observable<JSONObject> doGetAllHistoryOrder(HistoryOrderRequest.req request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GET_ALL_HISTORY_ORDER)
                .addHeaders("Authorization", mPrefHelper.getAuthToken())
                .addBodyParameter(request)
                .setPriority(Priority.MEDIUM)
                .build()
                .getJSONObjectObservable();
    }


}
