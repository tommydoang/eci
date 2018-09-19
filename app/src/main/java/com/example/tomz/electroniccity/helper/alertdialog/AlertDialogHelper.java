package com.example.tomz.electroniccity.helper.alertdialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by tomz on 12/8/2017.
 */

public class AlertDialogHelper {

    public static void createGeneralDialog(Context context, String message, String pBtn,
                                           String nBtn, AlertDialogHelpers alertCallBack){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(pBtn, (dialogInterface, i) -> alertCallBack.onPositiveClicked());
        builder.setNegativeButton(nBtn, (dialogInterface, i) -> alertCallBack.onNegativeClicked());
        builder.create().show();
    }

    public static void createGeneralDialog(Context context, String title, String message, String pBtn,
                                           String nBtn, AlertDialogHelpers alertCallBack){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(pBtn, (dialogInterface, i) -> alertCallBack.onPositiveClicked());
        builder.setNegativeButton(nBtn, (dialogInterface, i) -> alertCallBack.onNegativeClicked());
        builder.create().show();
    }
}
