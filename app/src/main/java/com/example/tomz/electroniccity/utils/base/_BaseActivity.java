package com.example.tomz.electroniccity.utils.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;

import com.example.tomz.electroniccity.MyApps;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.local.pref.AppPreferencesHelper;
import com.example.tomz.electroniccity.helper.IntentHelper;
import com.example.tomz.electroniccity.helper.alertdialog.AlertDialogHelper;
import com.example.tomz.electroniccity.helper.alertdialog.AlertDialogHelpers;
import com.example.tomz.electroniccity.utils.AppConstants;
import com.example.tomz.electroniccity.utils.MaskProcess;
import com.example.tomz.electroniccity.utils.NewEnableGPS;
import com.example.tomz.electroniccity.utils.services.CheckServices;
import com.example.tomz.electroniccity.utils.services.date.DateService;
import com.example.tomz.electroniccity.utils.services.location.LocationService;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public abstract class _BaseActivity<B extends ViewDataBinding, V extends BaseViewModel>
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
    @Inject AppPreferencesHelper mAppPreferencesHelper;
    @Inject IntentHelper mIntentHelper;
    @Inject MaskProcess mMaskProcess;
    @LayoutRes public abstract int getLayoutId();
    public abstract V getViewModel();
    public abstract int getBindingVariable();
    private TelephonyManager tm;
    private String deviceId, resultCipher;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doInjection();
        doDataBinding();
//        mViewModel.onViewCreated();
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        mNewEnableGPS.autoEnableGPS(this, this, googleApiClient);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED || ActivityCompat
                    .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED || ActivityCompat
                    .checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                requestPermissionsSafely(new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION},
                        AppConstants.REQUEST_PERMISSIONS_LOCATION);
                requestPermissionsSafely(new String[]{Manifest.permission.READ_PHONE_STATE},
                        AppConstants.REQUEST_PERMISSIONS_READ_PHONE_STATE);
                requestPermissionsSafely(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        AppConstants.REQUEST_PERMISSION_READ_EXTERNAL_STORAGE);
            } else {
                mCheckServices.isDateServiceRunning(this, DateService.class);
                mCheckServices.isLocationServiceRunning(this, LocationService.class);
                deviceId = mAppPreferencesHelper.getDeviceId();
                mAppPreferencesHelper.setIntFlag(1);
                getMaskingProcess(deviceId);
            }
        } else {
            mCheckServices.isDateServiceRunning(this, DateService.class);
            mCheckServices.isLocationServiceRunning(this, LocationService.class);
            if (!mAppPreferencesHelper.getDeviceId().isEmpty()) {
                deviceId = mAppPreferencesHelper.getDeviceId();
                getMaskingProcess(deviceId);
            } else {
                deviceId = tm.getDeviceId();
                Log.d("ALLOW KITKAT tes1", "imeii " + deviceId);
                mAppPreferencesHelper.setDeviceId(deviceId);
                mAppPreferencesHelper.setIntFlag(1);
                getMaskingProcess(deviceId);
            }
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
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    mNewEnableGPS.autoEnableGPS(this, this, googleApiClient);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mCheckServices.isLocationServiceRunning(getApplicationContext(),
                                    LocationService.class);
                        }
                    },1000);
                } else {
                    AlertDialogHelper.createGeneralDialog(this,
                            getString(R.string.text_title_enabled_permission_manual),
                            getString(R.string.text_enabled_location_permission_manual),
                            "GO TO PERMISSIONS", "CANCEL", new AlertDialogHelpers() {
                                @Override
                                public void onPositiveClicked() {
                                    Uri uri = Uri.fromParts("package",
                                            getPackageName(), null);
//                                    mIntentHelper.createIntent2Permissions(BaseActivity.this,
//                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//                                            uri); //dipake!!!!
                                }
                                @Override
                                public void onNegativeClicked() {
                                    //other logic
                                }
                            });
                }
                break;

            case AppConstants.REQUEST_PERMISSIONS_READ_PHONE_STATE:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    deviceId = tm.getDeviceId();
                    Log.d("ALLOW tes1", "imeii " + deviceId);
                    mAppPreferencesHelper.setDeviceId(deviceId);
                    mAppPreferencesHelper.setIntFlag(1);
                    getMaskingProcess(deviceId);
                } else {
                    AlertDialogHelper.createGeneralDialog(this,
                            getString(R.string.text_title_enabled_permission_manual),
                            getString(R.string.text_enabled_phone_permission_manual),
                            "GO TO PERMISSIONS", "CANCEL", new AlertDialogHelpers() {
                                @Override
                                public void onPositiveClicked() {
                                    Uri uri = Uri.fromParts("package",
                                            getPackageName(), null);
//                                    mIntentHelper.createIntent2Permissions(BaseActivity.this,
//                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//                                            uri); //dipake!!!!
                                }
                                @Override
                                public void onNegativeClicked() {
                                    //other logic
                                }
                            });

                }
                break;

            case AppConstants.REQUEST_PERMISSION_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d("READ ALLOW TES1", "MASUKKK!!!");
                } else {
                    Log.d("READ DENY TES1", "MASUKKK!!!");
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
//        mViewModel.onDestroyView();
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

    private void getMaskingProcess(String id){
        try {
            resultCipher = mMaskProcess.bytesToHex(mMaskProcess
                    .encrypt("eci|eci2018|" + id));
        } catch (Exception e) {
            Log.e("errResult tes1", e.getMessage());
            e.printStackTrace();
        }
    }
}
