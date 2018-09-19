package com.example.tomz.electroniccity.helper.snackbar;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by tomz on 12/10/2017.
 */

public class SnackBarHelper {

    public static void createWithoutAction (View view, String message, int duration){
        Snackbar snackbar = Snackbar.make(view, message, duration);
        snackbar.show();
    }

    public static void createWithAction (View view, String message, String actionText, int duration, SnackBarHelpers snackBarCallback){
        Snackbar snackbar = Snackbar.make(view, message, duration);
        snackbar.setAction(actionText, view1 -> {
            snackbar.dismiss();
            snackBarCallback.onActionClicked();
        });
        snackbar.show();
    }
}
