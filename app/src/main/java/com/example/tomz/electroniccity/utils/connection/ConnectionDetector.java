package com.example.tomz.electroniccity.utils.connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

public class ConnectionDetector {

    private int connectionType;

    public ConnectionDetector(){ }

    public int networkType(){
        return connectionType;
    }

    public boolean isConnectingToInternet(Context ctx){
        ConnectivityManager connectivity = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
//            Log.i("ConnectManager tes1", "!= null !!!!");
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            boolean isConnected = info != null && info.isConnected();
//            Log.i("CM_isConnected tes1", String.valueOf(isConnected));
            if(isConnected) {
                int networkType = info.getType();
                switch (networkType){
                    case ConnectivityManager.TYPE_MOBILE:
                        connectionType = 0;
                        return false;
                    case ConnectivityManager.TYPE_WIFI:
                        connectionType = 1;
                        return false;
                }
            } else {
                return true;
            }
        }
        return true;
    }

    public boolean isInternetAvailable() {
        @SuppressWarnings("UnusedAssignment") boolean pingResult = false;
        try {
            URL url = new URL("https://www.google.co.id");  // Change to TelingaDigital server.
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setConnectTimeout(10 * 1000);          // 10 s.
            urlc.connect();
            if (urlc.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                Log.i("Connection tes1", "Success!!!");
                return true;
            } else {
                Log.e("Connection tes1", "Failed!!!");
                pingResult = isInternetAvailableSecondRoute();
                return pingResult;
            }
        } catch (MalformedURLException e1) {
            Log.e("malformURL tes1", "MASUKK!!!");
            return false;
        } catch (IOException e) {
            Log.e("ioException tes1", "MASUKK!!!");
            return false;
        }
    }

    private boolean isInternetAvailableSecondRoute(){
        try {
            URL url = new URL("https://www.google.co.id");   // Change to "http://google.com" for www  test.
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setConnectTimeout(10 * 1000);          // 10 s.
            urlc.connect();
            if (urlc.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                Log.i("Connection tes1 2", "Success!!!");
                return true;
            } else {
                Log.e("Connection tes1 2", "Failed!!!");
                return false;
            }
        } catch (MalformedURLException e1) {
            Log.e("malformURL tes1 2", "MASUKK!!!");
            return false;
        } catch (IOException e) {
            Log.e("ioException tes1 2", "MASUKK!!!");
            return false;
        }
    }

}
