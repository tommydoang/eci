package com.example.tomz.electroniccity.page.side_menu.promo;

import android.annotation.TargetApi;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.R;

import com.example.tomz.electroniccity.adapter.side_menu.promo.PromoAdapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model.api.promo.DataPromoResponse;
import com.example.tomz.electroniccity.databinding.ActivityPromoBinding;
import com.example.tomz.electroniccity.helper.IntentHelper;
import com.example.tomz.electroniccity.utils.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class Promo extends BaseActivity<ActivityPromoBinding, PromoViewModel> implements PromoNavigator {

    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject ViewModelProvider.Factory mViewModelFactory;
    @Inject PromoViewModel mPromoViewModel;
    @Inject PromoAdapter mPromoAdapter;
    @Inject DataManager mDataManager;
    @Inject IntentHelper mIntentHelper;
    private ActivityPromoBinding mPromoBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_promo;
    }

    @Override
    public PromoViewModel getViewModel() {
        mPromoViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PromoViewModel.class);
        return mPromoViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.promo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPromoBinding = getViewDataBinding();
        mPromoViewModel.setNavigator(this);
        setupView();
        setupRVPromo();
    }

    private void setupView(){
        Toolbar mToolbar = mPromoBinding.toolbar;

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

    private void setupRVPromo(){
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPromoBinding.rvPromo.setLayoutManager(mLinearLayoutManager);
        mPromoBinding.rvPromo.setItemAnimator(new DefaultItemAnimator());
        mPromoBinding.rvPromo.setAdapter(mPromoAdapter);
        subscribeData();
    }

    private void subscribeData(){
        mPromoViewModel.getPromoLiveDataList().observe(this,
                dataPromoResponseList -> mPromoViewModel.setDataToList(dataPromoResponseList));
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onFailedBanner() {
        Log.d("onFailed tes1", "MASUKK!!!");
    }

    @Override
    public void updateList(List<DataPromoResponse> dataPromoResponseList) {
        mPromoAdapter.addItems(dataPromoResponseList);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

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
