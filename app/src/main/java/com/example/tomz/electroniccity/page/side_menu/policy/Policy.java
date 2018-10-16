package com.example.tomz.electroniccity.page.side_menu.policy;

import android.annotation.TargetApi;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.adapter.side_menu.policy.PolicyAdapter;
import com.example.tomz.electroniccity.data.model.api.policy.DataPolicyResponse;
import com.example.tomz.electroniccity.databinding.ActivityPolicyBinding;
import com.example.tomz.electroniccity.page.side_menu.promo.PromoViewModel;
import com.example.tomz.electroniccity.utils.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class Policy extends BaseActivity<ActivityPolicyBinding, PolicyViewModel> implements
        PolicyNavigator {

    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject ViewModelProvider.Factory mViewModelFactory;
    @Inject PolicyViewModel mPolicyViewModel;
    @Inject PolicyAdapter mPolicyAdapter;
    private ActivityPolicyBinding mPolicyBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_policy;
    }

    @Override
    public PolicyViewModel getViewModel() {
        mPolicyViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PolicyViewModel.class);
        return mPolicyViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.policy;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPolicyBinding = getViewDataBinding();
        mPolicyViewModel.setNavigator(this);
        setupView();
        setupRVPolicy();
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    private void setupView(){
        Toolbar mToolbar = mPolicyBinding.toolbar;

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white),
                    PorterDuff.Mode.SRC_ATOP);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mainStatusBarColor();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void mainStatusBarColor(){
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.newlightblue));
    }

    private void setupRVPolicy(){
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPolicyBinding.rvPolicy.setLayoutManager(mLinearLayoutManager);
        mPolicyBinding.rvPolicy.setItemAnimator(new DefaultItemAnimator());
        mPolicyBinding.rvPolicy.setAdapter(mPolicyAdapter);
        subscribeData();
    }

    private void subscribeData(){
        mPolicyViewModel.getPolicyLiveDataList().observe(this,
                dataPolicyResponses -> mPolicyViewModel.setDataToList(dataPolicyResponses));
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed() {

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updateList(List<DataPolicyResponse> dataPolicyResponseList) {
        mPolicyAdapter.addItems(dataPolicyResponseList);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
