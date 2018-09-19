package com.example.tomz.electroniccity.utils.connection;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tomz on 12/5/2017.
 */

public class ConnectionAsyncTask extends AsyncTask<Void, String, Boolean> {

    private HttpURLConnection mURLC;

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            URL url = new URL("https://www.google.co.id");  // Change to TelingaDigital server.
            mURLC = (HttpURLConnection) url.openConnection();
            mURLC.setConnectTimeout(10 * 1000);          // 10 s.
            mURLC.connect();
            if (mURLC.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                Log.i("Connection tes1", "Success!!!");
                mURLC.disconnect();
                return true;
            } else {
                Log.e("Connection tes1", "Failed!!!");
                mURLC.disconnect();
                return isInternetAvailableSecondRoute();
            }
        } catch (MalformedURLException e1) {
            Log.e("malformURL tes1", "MASUKK!!!");
            mURLC.disconnect();
            return false;
        } catch (IOException e) {
            Log.e("ioException tes1", "MASUKK!!!");
            mURLC.disconnect();
            return false;
        } finally {
            mURLC.disconnect();
        }
    }

    private boolean isInternetAvailableSecondRoute(){
        try {
            URL url = new URL("https://www.google.co.id");   // Change to "http://google.com" for www  test.
            mURLC = (HttpURLConnection) url.openConnection();
            mURLC.setConnectTimeout(10 * 1000);          // 10 s.
            mURLC.connect();
            if (mURLC.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                Log.i("Connection tes1 2", "Success!!!");
                mURLC.disconnect();
                return true;
            } else {
                Log.e("Connection tes1 2", "Failed!!!");
                mURLC.disconnect();
                return false;
            }
        } catch (MalformedURLException e1) {
            Log.e("malformURL tes1 2", "MASUKK!!!");
            mURLC.disconnect();
            return false;
        } catch (IOException e) {
            Log.e("ioException tes1 2", "MASUKK!!!");
            mURLC.disconnect();
            return false;
        }
    }

}
