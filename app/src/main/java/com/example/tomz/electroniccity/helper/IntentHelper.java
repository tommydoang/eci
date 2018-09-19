package com.example.tomz.electroniccity.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by tomz on 12/26/2017.
 */

public class IntentHelper {

    public void createIntent (Context context, Class<?> cls){
        Intent in = new Intent(context, cls);
        context.startActivity(in);
    }

    public void createIntentAction2Exit (Context context, String action){
        Intent in = new Intent(action);
        in.addCategory(Intent.CATEGORY_HOME);
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //***Change Here***
        context.startActivity(in);
    }

    public void createIntent2Permissions(Context context, String action, Uri dataUri){
        Intent in = new Intent(action);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        in.setData(dataUri);
        context.startActivity(in);
    }

    public void createIntent2Permissions(Context context, String action){
        Intent in = new Intent(action);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(in);
    }

    public void createIntentWithExtraData(Context context, Class<?> cls, ArrayList<String>data){
        Intent in = new Intent(context, cls);
        int howMany = data.size();
        for (int i = 0; i < howMany; i++ ){
            in.putExtra("TAG_" + i, data.get(i));
        }
        context.startActivity(in);
    }

    public void createIntentWithExtraData(Context context, Class<?> cls, int[] data){
        Intent in = new Intent(context, cls);
        int howMany = data.length;
        for (int i = 0; i < howMany; i++ ){
            in.putExtra("TAG_" + i, data[i]);
        }
        context.startActivity(in);
    }

    public void createIntentWithExtraData(Context context, Class<?> cls, int[] data, int flag){
        Intent in = new Intent(context, cls);
        in.addFlags(flag);
        int howMany = data.length;
        for (int i = 0; i < howMany; i++ ){
            in.putExtra("TAG_" + i, data[i]);
        }
        context.startActivity(in);
    }


}
