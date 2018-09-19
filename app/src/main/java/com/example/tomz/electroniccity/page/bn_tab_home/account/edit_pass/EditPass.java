package com.example.tomz.electroniccity.page.bn_tab_home.account.edit_pass;

import android.annotation.TargetApi;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.databinding.ActivityEditPassBinding;
import com.example.tomz.electroniccity.utils.base.BaseActivity;

import javax.inject.Inject;

public class EditPass extends BaseActivity<ActivityEditPassBinding, EditPassViewModel> implements
        EditPassNavigator {

    @Inject EditPassViewModel mEditPassViewModel;
    private Toolbar mToolbar;
    private ActivityEditPassBinding mEditPassBinding;
    private TextInputLayout mTilOldPass, mTilNewPass, mTilConfirmNewPass;
    private TextInputEditText mEtOldPass, mEtNewPass, mEtConfirmNewPass;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_pass;
    }

    @Override
    public EditPassViewModel getViewModel() {
        return mEditPassViewModel;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEditPassBinding = getViewDataBinding();
        setupView();
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    private void setupView(){
        mToolbar = mEditPassBinding.toolbar;
        mTilOldPass = mEditPassBinding.tilOldPassword;
        mTilNewPass = mEditPassBinding.tilNewPassword;
        mTilConfirmNewPass = mEditPassBinding.tilConfirmNewPassword;
        mEtOldPass = mEditPassBinding.etOldPassword;
        mEtNewPass = mEditPassBinding.etNewPassword;
        mEtConfirmNewPass = mEditPassBinding.etConfirmNewPassword;


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

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }



}
