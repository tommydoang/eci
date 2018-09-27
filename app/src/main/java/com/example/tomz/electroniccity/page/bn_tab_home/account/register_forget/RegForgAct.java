package com.example.tomz.electroniccity.page.bn_tab_home.account.register_forget;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.MyApps;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.databinding.ActivityRegforgBinding;
import com.example.tomz.electroniccity.helper.IntentHelper;
import com.example.tomz.electroniccity.helper.ToastHelper;
import com.example.tomz.electroniccity.utils.CommonUtils;
import com.example.tomz.electroniccity.utils.base.BaseActivity;
import com.example.tomz.electroniccity.utils.font.CustomTextViewLatoFont;

import javax.inject.Inject;

public class RegForgAct extends BaseActivity<ActivityRegforgBinding, RegForgViewModel> implements
        RegForgNavigator, View.OnClickListener{

    private Toolbar mToolbar;
    private EditText mUserFullName, mUserHandphone, mUserEmailForg;
    private LinearLayout mBtnRegister, mLayoutRegister, mLayoutForgetPassword, mBtnForgetPass;
    private CustomTextViewLatoFont tvHaveAccountAlready, tvNoAccountYet;
    private TextInputLayout mTilFullName, mTilEmail, mTilHandphone, mTilEmailForg;
    private ActivityRegforgBinding mRegForgBinding;
    private ImageView mIvHeaderRegForg;
    @Inject IntentHelper mIntentHelper;
    @Inject RegForgViewModel mRegForgViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_regforg;
    }

    @Override
    public RegForgViewModel getViewModel() {
        return mRegForgViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.regforg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRegForgBinding = getViewDataBinding();
//        mRegForgViewModel.setNavigator(this);
        setupView();
    }

    private void setupView(){
        mToolbar = mRegForgBinding.toolbar;
        mUserFullName = mRegForgBinding.etNamaLengkapRegister;
        mUserHandphone = mRegForgBinding.etNomorHpRegister;
        mUserEmailForg = mRegForgBinding.etEmailForget;
        mBtnRegister = mRegForgBinding.btnRegister;
        mLayoutRegister = mRegForgBinding.layoutForRegister;
        mLayoutForgetPassword = mRegForgBinding.layoutForForgetPassword;
        mBtnForgetPass = mRegForgBinding.btnNextForgetPass;
        tvHaveAccountAlready = mRegForgBinding.tvHaveAccountAlready;
        tvNoAccountYet = mRegForgBinding.tvNoAccountYet;
        mTilEmail = mRegForgBinding.tilEmail;
        mTilFullName = mRegForgBinding.tilNamaLengkap;
        mTilHandphone = mRegForgBinding.tilNomorHp;
        mTilEmailForg = mRegForgBinding.tilForgetPassEmail;
        mIvHeaderRegForg = mRegForgBinding.ivHeaderRegister;

        getFlagLayout();
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white),
                    PorterDuff.Mode.SRC_ATOP);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mainStatusBarColor();
        }
        textViewWithHTML();
        mBtnRegister.setOnClickListener(this);
        tvHaveAccountAlready.setOnClickListener(this);
        tvNoAccountYet.setOnClickListener(this);
        mBtnForgetPass.setOnClickListener(this);
        resizeAsBitmap();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void resizeAsBitmap(){
        Bitmap bMap = BitmapFactory.decodeResource(this.getResources(), R.drawable.newlogoblue);
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap,
                (int)(bMap.getWidth()*0.1), (int)(bMap.getHeight()*0.1), true);
        mIvHeaderRegForg.setImageBitmap(bMapScaled);
    }

    private void textViewWithHTML(){
        SpannableString content = new SpannableString(getString(R.string.text_have_account_yet));
        content.setSpan(new UnderlineSpan(), content.length()-13, content.length(), 0);
        tvHaveAccountAlready.setText(content);
        SpannableString content1 = new SpannableString(getString(R.string.text_no_account_yet));
        content1.setSpan(new UnderlineSpan(), content1.length()-13, content1.length(), 0);
        tvNoAccountYet.setText(content1);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void mainStatusBarColor(){
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.newlightblue));
    }

    private void getFlagLayout(){
        Intent intent = getIntent();
        int flagLayout = intent.getIntExtra("TAG_0", 0);
        if (flagLayout == 1){
            mLayoutRegister.setVisibility(View.VISIBLE);
            mToolbar.setTitle(getString(R.string.title_register));
        } else {
            mLayoutForgetPassword.setVisibility(View.VISIBLE);
            mToolbar.setTitle(getString(R.string.title_forget_password));
        }
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                ToastHelper.createToast(this, "DAFTAR CLICKED!!", Toast.LENGTH_LONG);
                onRegister();
                break;
            case R.id.tvHaveAccountAlready:
                finish();
                break;
            case R.id.tvNoAccountYet:
                mIntentHelper.createIntentWithExtraData(getApplicationContext(),
                        RegForgAct.class, new int[]{1}, Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                break;
            case R.id.btn_next_forget_pass:
                onResetPass();
                break;
        }
    }

    @Override
    public void onRegister() {
        String userFullName = mUserFullName.getText().toString().trim();
        Log.d("userFull tes1", userFullName);
        String userHP = mUserHandphone.getText().toString().trim();
        Log.d("userHP tes1", userHP);

        if (userFullName.isEmpty() /*&& userEmail.isEmpty()*/ && userHP.isEmpty()){
            mTilFullName.setError(getString(R.string.err_reg_fullname_empty));
            mTilHandphone.setError(getString(R.string.err_reg_handphone_empty));
        }

        if (userFullName.isEmpty()){
            mTilFullName.setError(getString(R.string.err_reg_fullname_empty));
        } else {
            mTilFullName.setErrorEnabled(false);
        }

        if (userHP.isEmpty()){
            mTilHandphone.setError(getString(R.string.err_reg_handphone_empty));
        } else {
            mTilHandphone.setErrorEnabled(false);
        }

        if (/*(!userEmail.isEmpty() && CommonUtils.isEmailValid(userEmail))
                &&*/ !userHP.isEmpty() && !userFullName.isEmpty()){
            mRegForgViewModel.registerRequest(/*userEmail,*/userFullName, userHP);
        }
    }

    @Override
    public void onSuccessRegister(String message) {
        Log.d("onSuccess tes1","REGISTER " + message);
    }

    @Override
    public void onFailedRegister(String message) {
        Log.d("onFailed tes1","REGISTER " + message);
    }

    @Override
    public void handleError(Throwable throwable) {
        Log.d("errReg tes1", throwable.getMessage()+"");
    }

    @Override
    public void onResetPass() {
        String userEmailForget = mUserEmailForg.getText().toString();

        if (userEmailForget.isEmpty()){
            mTilEmailForg.setError(null);
            mTilEmailForg.setErrorEnabled(false);
            new Handler().postDelayed(() -> mTilEmailForg
                    .setError(getString(R.string.err_reg_email_empty)), 180);
        } else {
            mTilEmailForg.setErrorEnabled(false);
        }

        if (!userEmailForget.isEmpty() && !CommonUtils.isEmailValid(userEmailForget)){
            mTilEmailForg.setError(getString(R.string.err_reg_email_invalid));
        } else {
            mTilEmailForg.setErrorEnabled(false);
        }

        if (!userEmailForget.isEmpty() && CommonUtils.isEmailValid(userEmailForget)){
            mRegForgViewModel.resetPasswordRequest(userEmailForget);
        }
    }

    @Override
    public void onSuccessResetPass(String message) {
        Log.d("onSuccesRESET tes1", message);
    }

    @Override
    public void onFailedResetPass(String message) {
        Log.d("onFailedRESET tes1", message);
    }
}
