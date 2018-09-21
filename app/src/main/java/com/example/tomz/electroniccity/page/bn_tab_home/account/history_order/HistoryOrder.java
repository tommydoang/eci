package com.example.tomz.electroniccity.page.bn_tab_home.account.history_order;

import android.annotation.TargetApi;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.adapter.HistoryOrderAdapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model.api.membership.DataHistoryOrderResponse;
import com.example.tomz.electroniccity.databinding.ActivityHistoryOrderBinding;
import com.example.tomz.electroniccity.utils.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class HistoryOrder extends BaseActivity<ActivityHistoryOrderBinding, HistoryOrderViewModel> implements
        HistoryOrderNavigator {

    @Inject HistoryOrderViewModel mHistoryOrderViewModel;
    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject HistoryOrderAdapter mHistoryOrderAdapter;
    @Inject DataManager mDataManager;
    private ActivityHistoryOrderBinding mHistoryOrderBinding;
    private Toolbar mToolbar;
    private RecyclerView mRVHistoryOrder;

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_order;
    }

    @Override
    public HistoryOrderViewModel getViewModel() {
        return mHistoryOrderViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHistoryOrderBinding = getViewDataBinding();
        setupView();
        setupRVHistoryOrder();
        callHistoryOrderApi();
    }

    @Override
    public void onSuccessHistoryOrder(String status) {

    }

    @Override
    public void onFailedHistoryOrder(String status) {

    }

    @Override
    public void handleError(Throwable throwable) {
        Log.e("onError tes1", throwable+"");
    }

    @Override
    public void updateList(List<DataHistoryOrderResponse> dataHistoryOrderResponseList) {
        mHistoryOrderAdapter.addItems(dataHistoryOrderResponseList);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    private void setupView(){
        mToolbar = mHistoryOrderBinding.toolbar;
        mRVHistoryOrder = mHistoryOrderBinding.rvHistoryOrder;

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

    private void setupRVHistoryOrder(){
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRVHistoryOrder.setLayoutManager(mLinearLayoutManager);
        mRVHistoryOrder.setItemAnimator(new DefaultItemAnimator());
        mRVHistoryOrder.setAdapter(mHistoryOrderAdapter);
        mRVHistoryOrder.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }

    private void callHistoryOrderApi(){
//        Log.e("userID tes1", mDataManager.getUserId());
        mHistoryOrderViewModel.getAllHistoryOrder("15194");
        new Handler().postDelayed(this::subscribeData, 880);
    }

    private void subscribeData(){
        mHistoryOrderViewModel.getLiveHistoryOrderDataList().observe(this,
                dataHistoryOrderResponses -> mHistoryOrderViewModel.setDataToList(dataHistoryOrderResponses));
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
