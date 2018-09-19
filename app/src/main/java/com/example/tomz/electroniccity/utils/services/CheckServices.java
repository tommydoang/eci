package com.example.tomz.electroniccity.utils.services;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by tomz on 10/03/2017.
 */

public class CheckServices {

    private static boolean isMyServiceRunning(Context ctx, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        assert manager != null;
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void isLocationServiceRunning(Context ctx, Class<?>serviceClass){
        if (isMyServiceRunning(ctx, serviceClass)){
            Log.i("serviceLoc tes1", "RUNNING!!!");
        } else {
            Log.e("errServiceLoc tes1", "NOT RUNNING!!!");
            ctx.startService(new Intent(ctx, serviceClass));
        }
    }

    public void isDateServiceRunning(Context ctx, Class<?>serviceClass){
        if (isMyServiceRunning(ctx, serviceClass)){
            Log.i("serviceDate tes1", "RUNNING!!!");
        } else {
            Log.e("errServiceDate tes1", "NOT RUNNING!!!");
            ctx.startService(new Intent(ctx, serviceClass));
        }
    }

}
