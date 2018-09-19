package com.example.tomz.electroniccity.page.side_menu.care;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.helper.ToastHelper;

import java.util.Objects;

public class CustomerCare extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            Objects.requireNonNull(mToolbar.getNavigationIcon())
                    .setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mainStatusBarColor();
        }

        RelativeLayout mCarePhone = findViewById(R.id.care_phone_layout);
        RelativeLayout mCareSMS = findViewById(R.id.care_message_layout);
        RelativeLayout mCareWhatsApp = findViewById(R.id.care_whatsApp_layout);
        RelativeLayout mCareEmail = findViewById(R.id.care_email_layout);

        mCareEmail.setOnClickListener(this);
        mCarePhone.setOnClickListener(this);
        mCareSMS.setOnClickListener(this);
        mCareWhatsApp.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void mainStatusBarColor(){
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.newlightblue));
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

    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.care_phone_layout:
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0211500032"));
                startActivity(callIntent);
                break;
            case R.id.care_message_layout:
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setData(Uri.parse("sms:" + "+628118500032"));
                startActivity(smsIntent);
                break;
            case R.id.care_whatsApp_layout:
                try {
                    Intent sendIntent = new Intent("android.intent.action.MAIN");
                    sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
                    sendIntent.putExtra("jid", PhoneNumberUtils
                            .stripSeparators("628118500032")+"@s.whatsapp.net");
                    startActivity(sendIntent);
                } catch(Exception e) {
                    ToastHelper.createToast(this,
                            "Please Install WhatsApp from Play Store", Toast.LENGTH_LONG);
                }
                break;
            case R.id.care_email_layout:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "customer@electronic-city.co.id", null));
                startActivity(Intent.createChooser(emailIntent, null));
                break;
        }
    }
}
