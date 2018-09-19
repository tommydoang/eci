package com.example.tomz.electroniccity.page.bn_tab_home.account.invite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.databinding.FragInviteBinding;
import com.example.tomz.electroniccity.helper.LoadFragmentHelper;
import com.example.tomz.electroniccity.helper.ToastHelper;
import com.example.tomz.electroniccity.utils.base.BaseFragment;

import javax.inject.Inject;

public class FragInvite extends BaseFragment<FragInviteBinding,FragInviteViewModel> implements InviteNavigator{

    @Inject FragInviteViewModel mFragInviteViewModel;
    @Inject DataManager mDataManager;
    @Inject LoadFragmentHelper mLoadFragmentHelper;
    private FragInviteBinding mFragInviteBinding;
    private EditText etUserHandphone;
    private Fragment mFragment;

    public FragInvite() {
        //Required empty public constructor
    }

    @Override
    public FragInviteViewModel getViewModel() {
        return mFragInviteViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.fraginvite;
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_invite;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragInviteViewModel.setNavigator(this);
        mFragment = this;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragInviteBinding = getViewDataBinding();
        etUserHandphone = mFragInviteBinding.etUserHandphone;
    }

    @Override
    public void sendLinkReq() {
        String userHP = etUserHandphone.getText().toString().trim();
        if (!userHP.isEmpty()){
            mFragInviteViewModel.linkRequest(userHP);
        }
    }

    @Override
    public void onSuccessLinkRequest(String status,String message) {
        Log.d("onSuccessLink tes1", "INVITE " + status);
        ToastHelper.createToast(getBaseActivity(), message, Toast.LENGTH_LONG);
        getBaseActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void handleError(Throwable throwable) {
        Log.d("errorInviter tes1", throwable.getMessage()+"");
    }

    @Override
    public void onFailedLinkRequest(String status) {
        Log.d("onFailedLink tes1", "INVITE " + status);
        ToastHelper.createToast(getBaseActivity(), getString(R.string.text_invite_member_failed), Toast.LENGTH_LONG);
        getBaseActivity().getSupportFragmentManager().popBackStackImmediate();
    }

}
