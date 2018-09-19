package com.example.tomz.electroniccity.helper.ping;

import com.example.tomz.electroniccity.utils.connection.ConnectionAsyncTask;

import java.util.concurrent.ExecutionException;

/**
 * Created by tomz on 12/10/2017.
 */

public class CheckInternetHelper {
    public static void startPing (boolean true_or_false, CheckInternetHelpers checkInternetCallback){
        try {
            if (new ConnectionAsyncTask().execute().get().equals(true_or_false)) {
                checkInternetCallback.onResultPing();
            } else {
                checkInternetCallback.onOtherResult();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
