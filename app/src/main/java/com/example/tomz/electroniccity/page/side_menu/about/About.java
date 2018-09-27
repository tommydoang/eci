package com.example.tomz.electroniccity.page.side_menu.about;

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
import com.example.tomz.electroniccity.adapter.side_menu.about.AboutAdapter;
import com.example.tomz.electroniccity.data.model.api.about.DataAboutUsResponse;
import com.example.tomz.electroniccity.databinding.ActivityAboutUsBinding;
import com.example.tomz.electroniccity.utils.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class About extends BaseActivity<ActivityAboutUsBinding,AboutViewModel> implements
        AboutNavigator {

    @Inject AboutViewModel mAboutViewModel;
    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject ViewModelProvider.Factory mViewModelFactory;
    @Inject AboutAdapter mAboutAdapter;
    private ActivityAboutUsBinding mAboutUsBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    public AboutViewModel getViewModel() {
        mAboutViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AboutViewModel.class);
        return mAboutViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.about;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAboutUsBinding = getViewDataBinding();
        mAboutViewModel.setNavigator(this);
        setupView();
        setupRVPromo();
    }

    private void setupView(){
        Toolbar mToolbar = mAboutUsBinding.toolbar;

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
        mAboutUsBinding.rvAboutUs.setLayoutManager(mLinearLayoutManager);
        mAboutUsBinding.rvAboutUs.setItemAnimator(new DefaultItemAnimator());
        mAboutUsBinding.rvAboutUs.setAdapter(mAboutAdapter);
        subscribeData();
    }

    private void subscribeData(){
        mAboutViewModel.getAboutLiveDataList().observe(this,
                dataAboutUsResponses ->  mAboutViewModel.setDataToList(dataAboutUsResponses));
    }


    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

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
    public void updateList(List<DataAboutUsResponse> dataAboutUsResponseList) {
        mAboutAdapter.addItems(dataAboutUsResponseList);
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
