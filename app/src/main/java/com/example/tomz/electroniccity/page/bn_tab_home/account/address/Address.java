package com.example.tomz.electroniccity.page.bn_tab_home.account.address;

import android.annotation.TargetApi;
import android.arch.lifecycle.ViewModelProvider;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.adapter.address.AddressAdapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;
import com.example.tomz.electroniccity.databinding.AddressBinding;
import com.example.tomz.electroniccity.helper.IntentHelper;
import com.example.tomz.electroniccity.utils.base.BaseActivity;
import com.example.tomz.electroniccity.utils.font.CustomTextViewLatoFont;

import java.util.List;

import javax.inject.Inject;

public class Address extends BaseActivity<AddressBinding,AddressViewModel> implements
        AddressNavigator {

    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject ViewModelProvider.Factory mViewModelFactory;
    @Inject AddressViewModel mAddressViewModel;
    @Inject AddressAdapter mAddressAdapter;
    @Inject DataManager mDataManager;
    @Inject IntentHelper mIntentHelper;
    private AddressBinding mAddressBinding;
    private RecyclerView mAddressRV;

    @Override
    public int getLayoutId() {
        return R.layout.address;
    }

    @Override
    public AddressViewModel getViewModel() {
        return mAddressViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.address;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAddressBinding = getViewDataBinding();
        mAddressViewModel.setNavigator(this);
        setupView();
        setupRVSendAddress();
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    private void setupView(){
        Toolbar mToolbar = mAddressBinding.toolbar;
        mAddressRV = mAddressBinding.rvAlamatKirim;
        CustomTextViewLatoFont mTitlePageAddress = mAddressBinding.titlePageAddress;

        mTitlePageAddress.setText(getString(R.string.title_alamat_saya));
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

    private void setupRVSendAddress(){
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAddressRV.setLayoutManager(mLinearLayoutManager);
        mAddressRV.setItemAnimator(new DefaultItemAnimator());
        mAddressRV.setAdapter(mAddressAdapter);
        new Handler().postDelayed(() -> mAddressViewModel
                .addressRequest(mDataManager.getUserId()),1200);
        new Handler().postDelayed(this::subscribeData,2000);

    }

    @Override
    public void handleError(Throwable throwable) {
        Log.e("errAddr tes1", throwable.getMessage()+"");
    }

    @Override
    public void updateList(List<DataAddressResponse> dataAddressResponseList) {
        mAddressAdapter.addItems(dataAddressResponseList);
    }

    private void subscribeData(){
        mAddressViewModel.getLiveAddressDataList().observe(this,
                dataAddressResponses -> mAddressViewModel.setDataToList(dataAddressResponses));
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
