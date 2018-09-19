package com.example.tomz.electroniccity.utils.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.tomz.electroniccity.utils.services.date.DateService;
import com.example.tomz.electroniccity.utils.services.location.LocationService;

public class RestartService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent pushIntent1 = new Intent(context, LocationService.class);
            context.startService(pushIntent1);

            Intent pushIntent2 =  new Intent(context, DateService.class);
            context.startService(pushIntent2);

            Log.d("restartServ tes1", "MASUKK!!!");
        }
    }
}
