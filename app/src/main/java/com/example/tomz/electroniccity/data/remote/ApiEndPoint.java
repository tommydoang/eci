package com.example.tomz.electroniccity.data.remote;

import com.example.tomz.electroniccity.BuildConfig;

public class ApiEndPoint {

    public ApiEndPoint() {
        // This class is not publicly instantiable
    }

    public static final String ENDPOINT_LOGIN = BuildConfig.BASE_URL + "/v1/login";

    public static final String ENDPOINT_REGISTER = BuildConfig.BASE_URL + "/v1/register";

    public static final String ENDPOINT_AUTH = BuildConfig.BASE_URL + "/auth/gettoken";

    public static final String ENDPOINT_INVITE_MEMBER = BuildConfig.BASE_URL + "/v1/invitation";

    public static final String ENDPOINT_FORGET_PASSWORD = BuildConfig.BASE_URL + "/v1/forgotpass";

    public static final String ENDPOINT_GET_ALL_PRODUCTS = BuildConfig.BASE_URL + "/v1/homeproducts";

    public static final String ENDPOINT_GET_BANNER = BuildConfig.BASE_URL + "/v1/getbanner";

    public static final String ENDPOINT_GET_SUPER_DEAL = BuildConfig.BASE_URL + "/v1/gettrafictpuller";

    public static final String ENDPOINT_GET_ALL_ADDRESS = BuildConfig.BASE_URL + "/v1/getCustommerAddress/";

    public static final String ENDPOINT_GET_ALL_HISTORY_ORDER = BuildConfig.BASE_URL + "/v1/historybelanja";

    public static final String ENDPOINT_GET_ALL_VALUE_ADD = BuildConfig.BASE_URL + "/v1/getvalueadded";

}
