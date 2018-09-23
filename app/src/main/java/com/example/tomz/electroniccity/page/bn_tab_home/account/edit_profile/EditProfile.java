package com.example.tomz.electroniccity.page.bn_tab_home.account.edit_profile;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.databinding.EditProfileBinding;
import com.example.tomz.electroniccity.helper.ToastHelper;
import com.example.tomz.electroniccity.utils.base.BaseActivity;
import com.example.tomz.electroniccity.utils.font.CustomTextViewLatoFont;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class EditProfile extends BaseActivity<EditProfileBinding, EditProfileViewModel>  implements
        EditProfileNavigator, View.OnClickListener, View.OnTouchListener{

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
    private PopupWindow popupWindow;
    private int tahun, bulan, hari;
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

    @SuppressLint("ClickableViewAccessibility")
    private void setupView(){
        mToolbar = mEditProfileBinding.toolbar;
        mEditProfileBirthday = mEditProfileBinding.etEditBirthdayProfile;
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

        parseUserDOB();
        mEditProfileBirthday.setOnTouchListener(this);
        mEditProfileBirthday.setText(setCustomDate(mDataManager.getUserBirthday()));
        mLayoutChangeEmail.setOnClickListener(this);
        mLayoutChangeHP.setOnClickListener(this);
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
                createPopUpChangeEmail(view);
                break;
            case R.id.btn_change_hp:
                createPopUpChangeHP(view);
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
        date = (view, tahun, bulan, hari) -> {
            mCalendar.set(Calendar.YEAR, tahun);
            mCalendar.set(Calendar.MONTH, bulan);
            mCalendar.set(Calendar.DAY_OF_MONTH, hari);
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
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @SuppressLint("SimpleDateFormat")
    private String setCustomDate(String date){
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate;
        String newDateString = null;
        try {
            newDate = spf.parse(date);
            spf = new SimpleDateFormat("dd/MM/yyyy");
            newDateString = spf.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDateString;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()){
            case R.id.et_edit_birthday_profile:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.performClick();
                    new DatePickerDialog(EditProfile.this, date, tahun,
                            bulan-1, hari).show();
                }
                break;
            case R.id.btn_cancel:
                ToastHelper.createToast(this, "BATAL CLICKED", Toast.LENGTH_LONG);
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    popupWindow.dismiss();
                }
                break;
            case R.id.btn_ubah_email:
                ToastHelper.createToast(this, "UBAH EMAIL CLICKED", Toast.LENGTH_LONG);
                break;
            case R.id.btn_ubah_hp:
                ToastHelper.createToast(this, "UBAH HP CLICKED", Toast.LENGTH_LONG);
                break;

        }
        return false;
    }

    private void parseUserDOB(){
        String dob = setCustomDate(mDataManager.getUserBirthday());
        StringBuilder sb = new StringBuilder();
        hari = Integer.parseInt(new StringBuilder().append(dob.charAt(0)).append(dob.charAt(1)).toString());
        bulan = Integer.parseInt(new StringBuilder().append(dob.charAt(3)).append(dob.charAt(4)).toString());
        tahun = Integer.parseInt(new StringBuilder().append(dob.charAt(6)).append(dob.charAt(7))
                .append(dob.charAt(8)).append(dob.charAt(9)).toString());
    }

    @SuppressLint({"ClickableViewAccessibility", "InflateParams"})
    private void createPopUpChangeEmail(View view){
        TextView mTvRegisteredEmail;
        LinearLayout mBtnCancelChangeEmail, mBtnSubmitChangeEmail;
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = null;
        if (inflater != null) {
            popupView = inflater.inflate(R.layout.popup_edit_email, null);
        }

        if (popupView != null) {
            mTvRegisteredEmail = popupView.findViewById(R.id.tvRegisteredEmail);
            mBtnCancelChangeEmail = popupView.findViewById(R.id.btn_cancel);
            mBtnSubmitChangeEmail = popupView.findViewById(R.id.btn_ubah_email);
            mTvRegisteredEmail.setText(mDataManager.getUserEmail());
            mBtnCancelChangeEmail.setOnTouchListener(this);
            mBtnSubmitChangeEmail.setOnTouchListener(this);
        }

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        Log.i("widthPopUP tes1", width+"");
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        Log.i("heightPopUP tes1", height+"");
        popupWindow = new PopupWindow(popupView, 810, height, true);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        dimBehind(popupWindow);
    }

    @SuppressLint({"ClickableViewAccessibility", "InflateParams"})
    private void createPopUpChangeHP(View view){
        TextView mTvRegisteredHP;
        LinearLayout mBtnCancelChangeEmail, mBtnSubmitChangeEmail;
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = null;
        if (inflater != null) {
            popupView = inflater.inflate(R.layout.popup_edit_hp, null);
        }

        if (popupView != null) {
            mTvRegisteredHP = popupView.findViewById(R.id.tvRegisteredHP);
            mBtnCancelChangeEmail = popupView.findViewById(R.id.btn_cancel);
            mBtnSubmitChangeEmail = popupView.findViewById(R.id.btn_ubah_hp);
            mTvRegisteredHP.setText(mDataManager.getUserHP());
            mBtnCancelChangeEmail.setOnTouchListener(this);
            mBtnSubmitChangeEmail.setOnTouchListener(this);
        }

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        Log.i("widthPopUP tes1", width+"");
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        Log.i("heightPopUP tes1", height+"");
        popupWindow = new PopupWindow(popupView, 820, height, true);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        dimBehind(popupWindow);
    }


    private static void dimBehind (PopupWindow popupWindow){
        View container;
        if (popupWindow.getBackground() == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                container = (View) popupWindow.getContentView().getParent();
            } else {
                container = popupWindow.getContentView();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                container = (View) popupWindow.getContentView().getParent().getParent();
            } else {
                container = (View) popupWindow.getContentView().getParent();
            }
        }
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.3f;
        if (wm != null) {
            wm.updateViewLayout(container, p);
        }
    }

}
