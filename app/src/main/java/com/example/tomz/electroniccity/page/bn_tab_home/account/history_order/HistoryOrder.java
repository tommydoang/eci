package com.example.tomz.electroniccity.page.bn_tab_home.account.history_order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.databinding.ActivityHistoryOrderBinding;
import com.example.tomz.electroniccity.utils.base.BaseActivity;

import javax.inject.Inject;

public class HistoryOrder extends BaseActivity<ActivityHistoryOrderBinding, HistoryOrderViewModel> implements
        HistoryOrderNavigator {

    @Inject HistoryOrderViewModel mHistoryOrderViewModel;
    private ActivityHistoryOrderBinding mHistoryOrderBinding;
    private Toolbar mToolbar;

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
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHistoryOrderBinding = getViewDataBinding();
    }

    @Override
    public void onSuccessHistoryOrder(String status) {

    }

    @Override
    public void onFailedHistoryOrder(String status) {

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    private void
}
