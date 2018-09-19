package com.example.tomz.electroniccity.helper;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by tomz on 12/8/2017.
 */

public class ToastHelper {

    public static void createToast(Context context, String message, int duration){
       Toast.makeText(context, message, duration).show();
    }

}
