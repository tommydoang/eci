package com.example.tomz.electroniccity.helper.firebase;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

public class AnalyticsClient {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Inject
    public AnalyticsClient() { }

    public FirebaseAnalytics init(Context ctx){
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(ctx);
        return mFirebaseAnalytics;
    }

//    public void traceLogEvent(String SCREEN_NAME){
//        Bundle bundle = new Bundle();
//        bundle.putString("screen_name", SCREEN_NAME);
//        mFirebaseAnalytics.logEvent("trace_screen_name", bundle);
//    }
}
