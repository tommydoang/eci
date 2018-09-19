package com.example.tomz.electroniccity.page.bn_tab_home.account.edit_profile;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.MyApps;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.databinding.EditProfileBinding;
import com.example.tomz.electroniccity.utils.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

public class EditProfile extends BaseActivity<EditProfileBinding, EditProfileViewModel>  implements
        EditProfileNavigator, View.OnFocusChangeListener {

    private Toolbar mToolbar;
    private Calendar mCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private EditProfileBinding mEditProfileBinding;
    private EditProfileViewModel mEditProfileViewModel;
    private EditText mEditProfileName, mEditProfileBirthday, mEditProfileEmail, mEditProfileHP;
    private TextInputLayout mTilNameProfile, mTilBirthdayProfile, mTilEmailProfile, mTilHPProfile;
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
        mEditProfileName = mEditProfileBinding.etEditNameProfile;
        mEditProfileBirthday = mEditProfileBinding.etEditBirthdayProfile;
        mEditProfileEmail = mEditProfileBinding.etEditEmailProfile;
        mEditProfileHP = mEditProfileBinding.etEditHpProfile;
        mTilNameProfile = mEditProfileBinding.tilEditNameProfile;
        mTilBirthdayProfile = mEditProfileBinding.tilEditBirthdayProfile;
        mTilEmailProfile = mEditProfileBinding.tilEditEmailProfile;
        mTilHPProfile = mEditProfileBinding.tilEditHpProfile;

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white),
                    PorterDuff.Mode.SRC_ATOP);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mainStatusBarColor();
        }

        mEditProfileName.setFocusable(false);
        mEditProfileName.setText(mDataManager.getUserFullName());
        mEditProfileBirthday.setText(mDataManager.getUserBirthday());
        mEditProfileEmail.setText(mDataManager.getUserEmail());
        mEditProfileHP.setText(mDataManager.getUserHP());
        mEditProfileBirthday.setOnFocusChangeListener(this);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void mainStatusBarColor(){
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.newlightblue));
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.et_edit_birthday_profile:
//                new DatePickerDialog(this, date, mCalendar.get(Calendar.YEAR),
//                        mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
//                break;
//        }
//    }

    @Override
    public void onChangeProfile() {

    }

    @Override
    public void onSuccessEdit(String message) {

    }

    @Override
    public void onFailedEdit(String message) {

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
                    new DatePickerDialog(this, date, mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
                } else {
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

}
