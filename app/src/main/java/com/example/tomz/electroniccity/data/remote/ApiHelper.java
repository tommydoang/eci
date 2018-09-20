package com.example.tomz.electroniccity.data.remote;

import com.example.tomz.electroniccity.page.bn_tab_home.account.AuthRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.ForgetPassRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.address.AllAddressRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.history_order.HistoryOrderRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.invite.InviteMemberRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.login.LoginRequest;
import com.example.tomz.electroniccity.page.bn_tab_home.account.register_forget.RegisterRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import io.reactivex.Observable;

public interface ApiHelper {

    Observable<JSONObject> doAuthApiCall(AuthRequest.req request);
    Observable<JSONObject> doLoginApiCall(LoginRequest.req request);
    Observable<JSONObject> doRegisterApiCall(RegisterRequest.req request);
    Observable<JSONObject> doInviteMemberApiCall(InviteMemberRequest.req request);
    Observable<JSONObject> doForgetPassApiCall(ForgetPassRequest.req request);
    Observable<JSONArray> doAllProductsApiCall();
    Observable<JSONObject> doGetBannerApiCall();
    Observable<JSONObject> doGetDealApiCall();
    Observable<JSONObject> doGetAllAddressApiCall(AllAddressRequest.req request);
    Observable<JSONObject> doGetAllHistoryOrderApiCall(HistoryOrderRequest.req request);
    Observable<JSONObject> doGetAllValueAddedApiCall();

}
