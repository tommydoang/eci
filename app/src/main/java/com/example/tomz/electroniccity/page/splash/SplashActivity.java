package com.example.tomz.electroniccity.page.splash;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.databinding.ActivitySplashScreenBinding;
import com.example.tomz.electroniccity.helper.IntentHelper;
import com.example.tomz.electroniccity.helper.ToastHelper;;
import com.example.tomz.electroniccity.helper.alertdialog.AlertDialogHelper;
import com.example.tomz.electroniccity.helper.alertdialog.AlertDialogHelpers;
import com.example.tomz.electroniccity.helper.firebase.AnalyticsClient;
import com.example.tomz.electroniccity.helper.ping.CheckInternetHelper;
import com.example.tomz.electroniccity.helper.ping.CheckInternetHelpers;
import com.example.tomz.electroniccity.helper.snackbar.SnackBarHelper;
import com.example.tomz.electroniccity.page.intro.IntroApps;
import com.example.tomz.electroniccity.page.main.MainActivity;
import com.example.tomz.electroniccity.utils.MaskProcess;
import com.example.tomz.electroniccity.utils.connection.ConnectionDetector;
import com.example.tomz.electroniccity.utils.base.BaseActivity;
import com.example.tomz.electroniccity.data.local.pref.AppPreferencesHelper;
import com.example.tomz.electroniccity.utils.services.CheckServices;
import com.example.tomz.electroniccity.utils.services.date.DateService;
import com.example.tomz.electroniccity.utils.services.location.LocationService;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class SplashActivity extends BaseActivity<ActivitySplashScreenBinding, SplashViewModel> implements
        /*HasActivityInjector,*/ SplashNavigator {

    @Inject AnalyticsClient mAnalyticsClient;
    @Inject IntentHelper mIntentHelper;
    @Inject ConnectionDetector mConnectionDetector;
    @Inject MaskProcess mMaskProcess;
    @Inject SplashViewModel mSplashViewModel;
    @Inject DataManager mDataManager;
    @Inject CheckServices mCheckServices;
    private IntentFilter filter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash_screen;
    }

    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        registerBroadcast();
        new Handler().postDelayed(this::checkConnection, 900);
        mSplashViewModel.setNavigator(this);
        loginDateStamp();
        if (mDataManager.getIntFlag() != 0) {
            new Handler().postDelayed(() -> {
                Log.d("delaySplash tes1", "MASUKK!!!");
                if (isPermissionAllowed())
                    getAuthKey();
            }, 5000);
        }
    }

    private void registerBroadcast(){
        LocalBroadcastManager.getInstance(SplashActivity.this)
                .registerReceiver(BReceiver, new IntentFilter("credit_session"));
        LocalBroadcastManager.getInstance(SplashActivity.this)
                .registerReceiver(BReceiver, new IntentFilter("permit_allowed"));
    }

    private void checkConnection(){
        if (mConnectionDetector.isConnectingToInternet(SplashActivity.this)) {
            SnackBarHelper.createWithoutAction(findViewById(R.id.splashCoorLayout),
                    getString(R.string.err_no_network),
                    Snackbar.LENGTH_SHORT);
        } else {
            CheckInternetHelper.startPing(false, new CheckInternetHelpers() {
                @Override
                public void onResultPing() {
                    SnackBarHelper.createWithoutAction(findViewById(R.id.splashCoorLayout),
                            getString(R.string.err_internet_access),
                            Snackbar.LENGTH_SHORT);
                }
                @Override
                public void onOtherResult() {
                    ToastHelper.createToast(getApplicationContext(),
                            getString(R.string.err_back_online),
                            Toast.LENGTH_LONG);
                    mAnalyticsClient.init(SplashActivity.this);
                }
            });
        }
    }

    private void isLoginAlready(){
        Log.i("ID SPLASH tes1", String.valueOf(mDataManager.getUserId()));
        if (mDataManager.getUserId().equals("")){
            if (mDataManager.getIntroFlag() == 0){
                mIntentHelper.createIntent(SplashActivity.this,
                        IntroApps.class);
                finish();
            } else {
                mIntentHelper.createIntent(SplashActivity.this,
                        MainActivity.class);
                finish();
            }
        } else {
            Log.i("userid tes1", "NOT EMPTY!!!");
            mIntentHelper.createIntent(SplashActivity.this,
                    MainActivity.class);
            finish();
        }
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void openIntroMainActivity(String status) {
        if (status.equals("SUCCSESS") || status.equals("SUCCESS")){
            isLoginAlready();
        } else {
            ToastHelper.createToast(this, getString(R.string.err_auth_failure),
                    Toast.LENGTH_LONG);
            isLoginAlready();
        }
        Log.d("authSplash tes1 1", status);
        Log.d("authSplash tes1 2", mDataManager.getAuthToken());
    }

    @Override
    public void handleError(Throwable throwable) {
        Log.e("errSplash tes1", ""+throwable.getMessage());
        ToastHelper.createToast(this, getString(R.string.err_server),
                Toast.LENGTH_LONG);
//        isLoginAlready();
        finish();
    }

    private BroadcastReceiver BReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("action tes1", action);
            if (action != null) {
                if (action.equals("credit_session")){
                    Log.d("credit_session tes1", "MASUKK!!!");
                    String messageFrom = intent.getStringExtra("credit_session");
                    Log.d("receiveFrom tes1", messageFrom);
                    switch (messageFrom) {
                        case "beda_tgl":
                            getAuthKey();
                            Log.d("splashDiffDate tes1", "MASUKKK!!");
                            break;
                        case "tgl_sama":
                            Log.d("splashSameDate tes1", "MASUKKK!!");
                            break;
                    }
                } else {
                    Log.d("permit_allow tes1", "MASUKK!!!");
                    String messageFrom = intent.getStringExtra("permit_allowed");
                    Log.d("receiveFrom tes1", messageFrom);
                    switch (messageFrom) {
                        case "allowed":
                            getAuthKey();
                            Log.d("ALLOWED tes1", "MASUKKK!!");
                            break;
                        case "denied":
                            Log.d("DENIED tes1", "MASUKKK!!");
                            permitDenied();
                            break;
                    }
                }
            }
        }
    };

    private void getAuthKey(){
        new Handler().postDelayed(() -> {
            try {
                if (mDataManager.getIntFlag() != 0) {
                    mSplashViewModel.getAuthApiCall(getResultCipher());
                }  else {
                    ToastHelper.createToast(this, getString(R.string.err_auth_failure),
                            Toast.LENGTH_LONG);
                    isLoginAlready();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 1000);
    }

    private void loginDateStamp(){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String format = sdf.format(new Date());
        mDataManager.setNewAuthDate(format);
        Log.d("dateStamp tes1", mDataManager.getNewAuthDate());
    }

    private void createFilter(){
        filter = new IntentFilter();
        filter.addAction("credit_session");
        filter.addAction("permit_allowed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(BReceiver);
    }

    private void permitDenied(){
        AlertDialogHelper.createGeneralDialog(this,
                getString(R.string.text_title_enabled_permission_manual),
                getString(R.string.text_enabled_permission_manual),
                "GO TO PERMISSIONS", "CANCEL", new AlertDialogHelpers() {
                    @Override
                    public void onPositiveClicked() {
                        Uri uri = Uri.fromParts("package",
                                getPackageName(), null);
                        mIntentHelper.createIntent2Permissions(SplashActivity.this,
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                uri);
                    }
                    @Override
                    public void onNegativeClicked() {
                        //other logic
                        ToastHelper.createToast(SplashActivity.this,
                                getString(R.string.text_some_or_all_permit_denied), Toast.LENGTH_LONG);
                        finish();
                    }
                });
    }
}
