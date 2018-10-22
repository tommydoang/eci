package com.example.tomz.electroniccity.utils.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.helper.IntentHelper;
import com.example.tomz.electroniccity.utils.AppConstants;
import com.example.tomz.electroniccity.utils.MaskProcess;
import com.example.tomz.electroniccity.utils.NewEnableGPS;
import com.example.tomz.electroniccity.utils.services.CheckServices;
import com.example.tomz.electroniccity.utils.services.date.DateService;
import com.example.tomz.electroniccity.utils.services.location.LocationService;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<B extends ViewDataBinding, V extends BaseViewModel>
        extends AppCompatActivity implements BaseFragment.Callback {

    private B mViewDataBinding;
    private V mViewModel;
    private GoogleApiClient googleApiClient;
    private boolean keyboardListenersAttached = false;
    private ViewGroup rootLayout;
    protected void onShowKeyboard(int keyboardHeight) {}
    protected void onHideKeyboard() {}
    @Inject CheckServices mCheckServices;
    @Inject NewEnableGPS mNewEnableGPS;
    @Inject DataManager mDataManager;
    @Inject IntentHelper mIntentHelper;
    @Inject MaskProcess mMaskProcess;
    @LayoutRes public abstract int getLayoutId();
    public abstract V getViewModel();
    public abstract int getBindingVariable();
    private TelephonyManager tm;
    private String deviceId, resultCipher;
    private boolean isPermissionAllowed = false;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doInjection();
        doDataBinding();
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED || ContextCompat
                    .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED || ContextCompat
                    .checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED || ContextCompat
                    .checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED ) {
                requestPermissionsSafely(new String[]{ Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.CALL_PHONE },
                        AppConstants.REQUEST_PERMISSIONS_LOCATION);
            } else {
                Log.d("ALLOW MARSH tes1 1", "MASUKKK!!!");
                mNewEnableGPS.autoEnableGPS(this, this, googleApiClient);
                mCheckServices.isDateServiceRunning(this, DateService.class);
                mCheckServices.isLocationServiceRunning(this, LocationService.class);
                if (!mDataManager.getDeviceId().isEmpty()) {
                    Log.d("ALLOW MARSH tes1 2", "MASUKKK!!!");
                    deviceId = mDataManager.getDeviceId();
                    Log.d("ALLOW MARSH tes1 2", "IMEII!!! " + deviceId);
                    getMaskingProcess(deviceId);
                } else {
                    Log.d("ALLOW MARSH tes1 3", "MASUKKK!!!");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        deviceId = tm.getImei();
                        mDataManager.setDeviceId(deviceId);
                        mDataManager.setIntFlag(1);
                        getMaskingProcess(deviceId);
                    } else {
                        deviceId = tm.getDeviceId();
                        Log.d("ALLOW MARSH tes1 4", "imeii " + deviceId);
                        mDataManager.setDeviceId(deviceId);
                        mDataManager.setIntFlag(1);
                        getMaskingProcess(deviceId);
                    }
                }
                isPermissionAllowed = true;
            }
        } else {
            mCheckServices.isDateServiceRunning(this, DateService.class);
            mCheckServices.isLocationServiceRunning(this, LocationService.class);
            if (!mDataManager.getDeviceId().isEmpty()) {
                deviceId = mDataManager.getDeviceId();
                getMaskingProcess(deviceId);
            } else {
                deviceId = tm.getDeviceId();
                Log.d("ALLOW KITKAT tes1", "imeii " + deviceId);
                mDataManager.setDeviceId(deviceId);
                mDataManager.setIntFlag(1);
                getMaskingProcess(deviceId);
            }
            isPermissionAllowed = true;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    private void doInjection(){
        AndroidInjection.inject(this);
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case AppConstants.REQUEST_PERMISSIONS_LOCATION:
                if (grantResults.length > 0){
                    Log.d("permit length tes1", permissions.length+"");
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        Log.d("LOC ALLOW tes1", "FINE LOCATION --- MASUK");
                        mNewEnableGPS.autoEnableGPS(this, this, googleApiClient);
                        new Handler().postDelayed(() -> mCheckServices
                                .isLocationServiceRunning(getApplicationContext(),
                                        LocationService.class), 2000);
                    }
                    if (grantResults[1] == PackageManager.PERMISSION_GRANTED){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            deviceId = tm.getImei();
                        } else {
                            deviceId = tm.getDeviceId();
                        }
                        Log.d("PRMIT ALLOW tes1", "IMEIII " + deviceId);
                        mDataManager.setDeviceId(deviceId);
                        getMaskingProcess(deviceId);
                    }
                    if (grantResults[2] == PackageManager.PERMISSION_GRANTED){
                        Log.d("READ ALLOW TES1", "EXTERNAL STORAGE --- MASUKKK!!!");
                        mDataManager.setIntFlag(1);
                    }
                    if (grantResults[3] == PackageManager.PERMISSION_GRANTED){
                        Log.d("CALL ALLOW TES1", "EXTERNAL STORAGE --- MASUKKK!!!");
                    }
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                            grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                            grantResults[2] == PackageManager.PERMISSION_GRANTED &&
                            grantResults[3] == PackageManager.PERMISSION_GRANTED) {
                        customSendBroadcast("allowed");
                    } else {
                        customSendBroadcast("denied");
                    }
                }
                break;
        }
    }

    private ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener =
            new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            int heightDiff = rootLayout.getRootView().getHeight() - rootLayout.getHeight();
            int contentViewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();

            if(heightDiff <= contentViewTop){
                onHideKeyboard();
            } else {
                int keyboardHeight = heightDiff - contentViewTop;
                onShowKeyboard(keyboardHeight);
            }
        }
    };

    protected void attachKeyboardListeners() {
        if (keyboardListenersAttached) {
            return;
        }
        rootLayout = findViewById(R.id.drawerLayout);
        rootLayout.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);
        keyboardListenersAttached = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (keyboardListenersAttached) {
            removeOnGlobalLayoutListener(rootLayout, keyboardLayoutListener);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private static void removeOnGlobalLayoutListener(View v,
                                                     ViewTreeObserver.OnGlobalLayoutListener listener){
        v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    }

    private void doDataBinding(){
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    public B getViewDataBinding() {
        return mViewDataBinding;
    }

    public String getResultCipher(){
        return resultCipher;
    }

    public boolean isPermissionAllowed(){
        return isPermissionAllowed;
    }

    private void getMaskingProcess(String id){
        try {
            resultCipher = mMaskProcess.bytesToHex(mMaskProcess
                    .encrypt("eci|eci2018|" + id));
        } catch (Exception e) {
            Log.e("errResult tes1", e.getMessage());
            e.printStackTrace();
        }
    }

    private void customSendBroadcast(String str){
        Intent intent = new Intent("permit_allowed");
        intent.putExtra("permit_allowed", str);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}
