package com.example.tomz.electroniccity.page.bn_tab_home.account.edit_profile;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.MyApps;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.databinding.EditProfileBinding;
import com.example.tomz.electroniccity.helper.ToastHelper;
import com.example.tomz.electroniccity.utils.base.BaseActivity;
import com.example.tomz.electroniccity.utils.font.CustomTextViewLatoFont;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

public class EditProfile extends BaseActivity<EditProfileBinding, EditProfileViewModel>  implements
        EditProfileNavigator, View.OnFocusChangeListener, View.OnClickListener {

    private Toolbar mToolbar;
    private Calendar mCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private EditProfileBinding mEditProfileBinding;
    private EditProfileViewModel mEditProfileViewModel;
    private EditText mEditProfileName, mEditProfileBirthday, mEditProfileEmail, mEditProfileHP;
    private TextInputLayout mTilNameProfile, mTilBirthdayProfile, mTilEmailProfile, mTilHPProfile;
    private CustomTextViewLatoFont mTvName, mTvEmail, mTvHp;
    private RelativeLayout mLayoutChangeEmail, mLayoutChangeHP;
    private LinearLayout mLayoutBtnSaveProfile;
    @Inject DataManager mDataManager;

    @Override
    public int getLayoutId() {
        return R.layout.edit_profile;
    }

    @Override
    public EditProfileViewModel getViewModel() {
        return mEditProfileViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.editprofile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEditProfileBinding = getViewDataBinding();
        mCalendar = Calendar.getInstance();
        setupView();
        dialogDatePicker();
    }


    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    private void setupView(){
        mToolbar = mEditProfileBinding.toolbar;
        mEditProfileBirthday = mEditProfileBinding.etEditBirthdayProfile;
        mTilBirthdayProfile = mEditProfileBinding.tilEditBirthdayProfile;
        mTvEmail = mEditProfileBinding.textEmail;
        mTvHp = mEditProfileBinding.textHandphone;
        mTvName = mEditProfileBinding.textNamaUser;
        mLayoutChangeEmail = mEditProfileBinding.btnChangeEmail;
        mLayoutChangeHP = mEditProfileBinding.btnChangeHp;
        mLayoutBtnSaveProfile = mEditProfileBinding.btnSaveEditProfile;

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white),
                    PorterDuff.Mode.SRC_ATOP);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mainStatusBarColor();
        }

        mEditProfileBirthday.setText(mDataManager.getUserBirthday());
        mLayoutChangeEmail.setOnClickListener(this);
        mLayoutChangeHP.setOnClickListener(this);
        mEditProfileBirthday.setOnFocusChangeListener(this);
        mTvName.setText(mDataManager.getUserName());
        mTvEmail.setText(mDataManager.getUserEmail());
        mTvHp.setText(mDataManager.getUserHP());
        mLayoutBtnSaveProfile.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void mainStatusBarColor(){
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.newlightblue));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_change_email:
                ToastHelper.createToast(this, "CHANGE EMAIL!!!", Toast.LENGTH_LONG);
                break;
            case R.id.btn_change_hp:
                ToastHelper.createToast(this, "CHANGE PHONE NUMBER!!!", Toast.LENGTH_LONG);
                break;
            case R.id.btn_save_edit_profile:
                ToastHelper.createToast(this, "CHANGE SAVE", Toast.LENGTH_LONG);
                break;
        }
    }

    @Override
    public void onChangeProfile() {
        ToastHelper.createToast(this, "NEW PROFILE SAVED!!!", Toast.LENGTH_LONG);
    }

    @Override
    public void onSuccessEdit(String message) {

    }

    @Override
    public void onFailedEdit(String message) {

    }

    @Override
    public void onChangeEmail() {
        ToastHelper.createToast(this, "CHANGE EMAIL!!!", Toast.LENGTH_LONG);
    }

    @Override
    public void onChangeHP() {
        ToastHelper.createToast(this, "CHANGE PHONE NUMBER!!!", Toast.LENGTH_LONG);
    }

    private void dialogDatePicker(){
        date = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, monthOfYear);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,
                new Locale("in", "ID"));
        mEditProfileBirthday.setText(sdf.format(mCalendar.getTime()));
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.et_edit_birthday_profile:
                if (b){
                    Log.d("focusTRUE tes1", "MASUKK!!!");
//                    hideKeyboard(view);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            new DatePickerDialog(EditProfile.this, date, mCalendar.get(Calendar.YEAR),
                                    mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
                        }
                    }, 1280);

                    new Handler().postDelayed(() -> hideKeyboard(view),700);
                } else {
                    Log.d("focusFALSE tes1", "MASUKK!!!");
                    new DatePickerDialog(this, date, mCalendar.get(Calendar.YEAR),
                            mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).dismiss();
                }
                break;
        }
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

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
