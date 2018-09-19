package com.example.tomz.electroniccity.page.bn_tab_home.account;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.local.pref.AppPreferencesHelper;
import com.example.tomz.electroniccity.data.local.pref.PreferencesHelper;
import com.example.tomz.electroniccity.databinding.FragAccBinding;
import com.example.tomz.electroniccity.helper.IntentHelper;
import com.example.tomz.electroniccity.helper.LoadFragmentHelper;
import com.example.tomz.electroniccity.helper.ToastHelper;
import com.example.tomz.electroniccity.page.bn_tab_home.account.address.Address;
import com.example.tomz.electroniccity.page.bn_tab_home.account.edit_pass.EditPass;
import com.example.tomz.electroniccity.page.bn_tab_home.account.edit_profile.EditProfile;
import com.example.tomz.electroniccity.page.bn_tab_home.account.invite.FragInvite;
import com.example.tomz.electroniccity.page.bn_tab_home.account.login.LoginNavigator;
import com.example.tomz.electroniccity.page.bn_tab_home.account.register_forget.RegForgAct;
import com.example.tomz.electroniccity.utils.base.BaseFragment;
import com.example.tomz.electroniccity.utils.font.CustomTextViewLatoFont;

import javax.inject.Inject;

public class FragAcc extends BaseFragment<FragAccBinding, FragAccViewModel> implements
        LoginNavigator, View.OnClickListener {

    @Inject DataManager mDataManager;
    @Inject IntentHelper mIntentHelper;
    @Inject FragAccViewModel mFragAccViewModel;
    @Inject LoadFragmentHelper mLoadFragmentHelper;
    @Inject PreferencesHelper mPreferencesHelper;
    private FragAccBinding mFragAccBinding;
    private EditText etUsername;
    private TextInputLayout tilUsername, tilPassword;
    private TextInputEditText etPassword;
    private ScrollView svLayoutAfterLogin;
    private LinearLayout mLayoutBeforeLogin, mBtnActivateMember, mLayoutUserProfile,
            mLayoutUserAddress, mLayoutUserPassword, mLayoutUserWishlist,
            mLayoutUserHistoryOrder, mLayoutUserHistoryRedeem, mLayoutuserLogout;
    private CustomTextViewLatoFont tvRegister, tvForgetPass, tvMemberName, tvMemberId;
    private Fragment mFragment;
    private String isLogin;

    public FragAcc(){
        //Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragAccViewModel.setNavigator(this);
        isLogin = mDataManager.getUserId();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragAccBinding = getViewDataBinding();
        viewBinding();
        textViewWithHTML();
        if (!isLogin.isEmpty()){
            mLayoutBeforeLogin.setVisibility(View.GONE);
            svLayoutAfterLogin.setVisibility(View.VISIBLE);
            tvMemberName.setText(mDataManager.getUserFullName());
            tvMemberId.setText(mDataManager.getUserMemberID());
        } else {
            mLayoutBeforeLogin.setVisibility(View.VISIBLE);
            svLayoutAfterLogin.setVisibility(View.GONE);
        }
    }

    private void viewBinding(){
        etUsername = mFragAccBinding.etEmailOrUsername;
        etPassword = mFragAccBinding.etPassword;
        tvRegister = mFragAccBinding.tvUserRegister;
        tvForgetPass = mFragAccBinding.tvForgetPassword;
        mLayoutUserProfile = mFragAccBinding.userProfile;
        mLayoutUserAddress = mFragAccBinding.userSendAddress;
        mLayoutUserPassword = mFragAccBinding.userChangePassword;
        mLayoutUserWishlist = mFragAccBinding.userWishlist;
        mLayoutUserHistoryOrder = mFragAccBinding.userHistoryOrder;
        mLayoutUserHistoryRedeem = mFragAccBinding.userHistoryRedeem;
        mLayoutuserLogout = mFragAccBinding.userLogout;
        tilUsername = mFragAccBinding.tilEmailUsername;
        tilPassword = mFragAccBinding.tilPassword;
        mLayoutBeforeLogin = mFragAccBinding.layoutNotLogin;
        svLayoutAfterLogin = mFragAccBinding.layoutAfterLogin;
        tvMemberName = mFragAccBinding.tvMemberName;
        tvMemberId = mFragAccBinding.tvMemberID;
        mBtnActivateMember = mFragAccBinding.btnActivateMember;

        tvRegister.setOnClickListener(this);
        tvForgetPass.setOnClickListener(this);
        mLayoutUserProfile.setOnClickListener(this);
        mLayoutUserAddress.setOnClickListener(this);
        mLayoutUserPassword.setOnClickListener(this);
        mLayoutUserWishlist.setOnClickListener(this);
        mLayoutUserHistoryOrder.setOnClickListener(this);
        mLayoutUserHistoryRedeem.setOnClickListener(this);
        mLayoutuserLogout.setOnClickListener(this);
        mBtnActivateMember.setOnClickListener(this);
    }

    private void textViewWithHTML(){
        SpannableString content = new SpannableString(getString(R.string.text_no_account_yet));
        content.setSpan(new UnderlineSpan(), content.length()-13, content.length(), 0);
        tvRegister.setText(content);
        SpannableString forgetPass = new SpannableString(getString(R.string.text_forget_pass));
        forgetPass.setSpan(new UnderlineSpan(), 0, forgetPass.length(), 0);
        tvForgetPass.setText(forgetPass);
    }

    @Override
    public FragAccViewModel getViewModel() {
        return mFragAccViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.fragacc;
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_acc;
    }

    @Override
    public void handleError(Throwable throwable) {
        ToastHelper.createToast(getContext(), "GAGAL LOGIN",
                Toast.LENGTH_LONG);
    }

    @Override
    public void login() {
        String username = etUsername.getText().toString().trim();
        Log.d("userFragAcc tes1", username);
        String password = etPassword.getText().toString().trim();
        Log.d("passFragAcc tes1", password);

        if (username.isEmpty() && password.isEmpty()){
            tilUsername.setError(getString(R.string.err_username_email_empty));
            tilPassword.setError(getString(R.string.err_password_empty));
        }

        if (username.isEmpty()){
            tilUsername.setError(getString(R.string.err_username_email_empty));
        } else {
            tilUsername.setErrorEnabled(false);
        }

        if (password.isEmpty()){
            tilPassword.setError(getString(R.string.err_password_empty));
        } else {
            tilPassword.setErrorEnabled(false);
        }

        if (!username.isEmpty() && !password.isEmpty()){
            mFragAccViewModel.loginRequest(username,password);
        }
    }

    @Override
    public void onSuccessLogin(String statusResponse) {
        Log.d("onSuccessLogin tes1", statusResponse);
        if (statusResponse.equals("SUCCESS")){
            mLayoutBeforeLogin.setVisibility(View.GONE);
            svLayoutAfterLogin.setVisibility(View.VISIBLE);
            //get username and member_id
            tvMemberName.setText(mDataManager.getUserFullName());
            tvMemberId.setText(mDataManager.getUserMemberID());
           hideSoftKey();
        } else {
            mLayoutBeforeLogin.setVisibility(View.VISIBLE);
            svLayoutAfterLogin.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailedLogin(String status, String message) {
        if (status.equals("ERROR")){
            ToastHelper.createToast(getBaseActivity(), message, Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvUserRegister:
                mIntentHelper.createIntentWithExtraData(getBaseActivity(),
                        RegForgAct.class, new int[]{1});
//                Crashlytics.getInstance().crash(); // force crash test
                break;
            case R.id.tvForgetPassword:
                mIntentHelper.createIntentWithExtraData(getBaseActivity(),
                        RegForgAct.class, new int[]{-1});
                break;
            case R.id.btn_activate_member:
                mFragment = new FragInvite();
                mLoadFragmentHelper.loadFragment(getBaseActivity(),R.id.main_container, mFragment);
                break;
            case R.id.user_logout:
                mPreferencesHelper.removeAllPreferences();
                mFragment = new FragAcc();
                mLoadFragmentHelper.loadFragment(getBaseActivity(), R.id.main_container, mFragment);
                break;
            case R.id.user_profile:
                mIntentHelper.createIntent(getBaseActivity(), EditProfile.class);
                break;
            case R.id.user_send_address:
                mIntentHelper.createIntent(getBaseActivity(), Address.class);
                break;
            case R.id.user_change_password:
                mIntentHelper.createIntent(getBaseActivity(), EditPass.class);
                break;
            case R.id.user_wishlist:
                break;
            case R.id.user_history_order:
                break;

        }
    }

    private void hideSoftKey(){
        View view = getBaseActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getBaseActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
