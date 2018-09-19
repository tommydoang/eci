package com.example.tomz.electroniccity.utils.services.date;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.local.pref.AppPreferencesHelper;
import com.example.tomz.electroniccity.page.bn_tab_home.account.AuthRequest;
import com.example.tomz.electroniccity.utils.MaskProcess;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by tomz on 8/14/2017.
 */

public class DateService extends Service {

    private AlarmManager alarmMgr;
    private Intent intent2;
    private Calendar mCalendar;
    private String dateFormat, timeFormat, dateTime;
    private SimpleDateFormat sdfDate;
    @Inject DataManager mDataManager;
//    @Inject AppPreferencesHelper mAppPreferencesHelper;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onCreate(){
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());

        Intent intent = new Intent(this, DateService.class);
        PendingIntent pIntent = PendingIntent.getService(this, 0, intent, 0);

        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(),
                600 * 60 * 1000, pIntent);
        Log.i("date service tes1", "masukk");
        AndroidInjection.inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startSessionCount();
        this.intent2 = intent;
        return START_STICKY;
    }

    private void customSendBroadcast(String str){
        Log.d("sendBroadServ tes1", "MASUKK!!!");
        Intent intent = new Intent("credit_session");
        intent.putExtra("credit_session", str);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void startSessionCount(){
        try {
            if (mDataManager.getLastAuthDate().isEmpty()) {
                mDataManager.setLastAuthDate(mDataManager.getNewAuthDate());
                Date lastAuthDate = sdfDate.parse(mDataManager.getLastAuthDate());
                Log.i("lastDate tes1 1", String.valueOf(lastAuthDate));
                Date newAuthDate = sdfDate.parse(mDataManager.getNewAuthDate());
                Log.i("newDate tes1 1", String.valueOf(newAuthDate));
                isDateSame(newAuthDate, lastAuthDate);
            } else {
                Date lastAuthDate = sdfDate.parse(mDataManager.getLastAuthDate());
                Log.i("lastDate tes1 2", String.valueOf(lastAuthDate));
                Date newAuthDate = sdfDate.parse(mDataManager.getNewAuthDate());
                Log.i("newDate tes1 2", String.valueOf(newAuthDate));
                isDateSame(newAuthDate, lastAuthDate);
            }
        } catch (Exception e){
            Log.e("compareDate tes1", e.getMessage()+"");
        }
    }

    @Override
    public void onDestroy(){
        Log.v("DateService tes1","Service killed");
//        stopService(intent2);
//        stopSelf();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void isDateSame(Date currentDate, Date lastDate){
        if (currentDate.after(lastDate)){
            //send broadcast as trigger to get auth key
            Log.d("diffDate tes1", "MASUKKK!!");
            mDataManager.setLastAuthDate(mDataManager.getNewAuthDate());
            customSendBroadcast("beda_tgl");
        }  else if (lastDate.equals(currentDate)) {
            //send broadcast as trigger to get auth key
            customSendBroadcast("tgl_sama");
            Log.d("sameDate tes1", "MASUKKK!!");
        }
    }
}
