package com.example.tomz.electroniccity.utils.services.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.tomz.electroniccity.data.local.pref.AppPreferencesHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by tomz on 6/15/2017.
 */

public class LocationService extends Service implements LocationListener {

    private double userlat, userlng;
    private Intent intent2;
    private LocationManager locationManager;
    private Geocoder mGeocoder;
    private List<Address> mAddress;

    @Inject AppPreferencesHelper mPreferencesHelper;

    @Override
    public void onCreate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Intent intent = new Intent(this, LocationService.class);
        PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), 10 * 60 * 1000, pintent);
        }
        Log.i("gps service tes1", "masukk");
        mGeocoder = new Geocoder(this, Locale.getDefault());
        mAddress = new ArrayList<>();
        AndroidInjection.inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTrackGPS(intent, startId);
        this.intent2 = intent;
        return START_STICKY;
    }

    private void startTrackGPS(Intent intent, int startId) {
        // Getting LocationManager object from System Service LOCATION_SERVICE
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            Log.i("locMan tes1 1", "!= null!!!!!");
            @SuppressLint("MissingPermission")
            Location locGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(locGPS != null) {
                userlat = locGPS.getLatitude();
                Log.d("userlat tes1 1", String.valueOf(userlat));
                userlng = locGPS.getLongitude();
                Log.d("userlng tes1 1", String.valueOf(userlng));

                mPreferencesHelper.setUserLat(String.valueOf(userlat));
                mPreferencesHelper.setUserLng(String.valueOf(userlng));
                Log.d("locLatLK tes1 1", mPreferencesHelper.getUserLat());
                Log.d("locLngLK tes1 1", mPreferencesHelper.getUserLng());
            } else {
                Log.i("locGPS tes1 2", "null!!!!");
                @SuppressLint("MissingPermission")
                Location locNET = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if(locNET != null) {
                    Log.i("locNET tes1 2", "!= null!!!!");
                    userlat = locNET.getLatitude();
                    Log.d("userlat tes1 2", String.valueOf(userlat));
                    userlng = locNET.getLongitude();
                    Log.d("userlng tes1 2", String.valueOf(userlng));

                    mPreferencesHelper.setUserLat(String.valueOf(userlat));
                    mPreferencesHelper.setUserLng(String.valueOf(userlng));
                    Log.d("locLatLK tes1 2", mPreferencesHelper.getUserLat());
                    Log.d("locLngLK tes1 2", mPreferencesHelper.getUserLng());
                } else {
                    Log.i("locNET tes1 2", "null!!!!");
                }
            }
        } else {
            Log.i("locMan tes1 2", "null!!!!");
        }

        if(mAddress.size()>0) {
            getCurrLocationName();
        }

        if((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    600000, 90.0f, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    600000, 90.0f, this);
        }
    }

    @Override
    public void onDestroy(){
        Log.v("GPSService tes1","Service killed");
//        stopService(intent2);
//        stopSelf();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        // Getting latitude longitude of the current location
        double GPSlat = location.getLatitude();
        Log.d("lat onChange tes1", String.valueOf(GPSlat));
        double GPSlng = location.getLongitude();
        Log.d("lng onChange tes1", String.valueOf(GPSlng));

        if(GPSlat != Double.parseDouble("0.0") && GPSlng != Double.parseDouble("0.0")) {
            if (GPSlat != userlat && GPSlng != userlng) {
                mPreferencesHelper.setUserLat(String.valueOf(userlat));
                mPreferencesHelper.setUserLng(String.valueOf(userlng));
            } else {
                mPreferencesHelper.setUserLat(String.valueOf(userlat));
                mPreferencesHelper.setUserLng(String.valueOf(userlng));
            }
        }

        double distancePoint = calculateDistance(GPSlat, GPSlng, userlat, userlng);
        Log.d("dis tes1", String.valueOf(distancePoint));
        if (distancePoint > 1.0) {
            // called when the listener is notified with a location update
            sendLocation2Server();
            Log.d("distance tes1", String.valueOf(distancePoint));
        }

        if (GPSlat != 0 && GPSlng != 0){
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                locationManager.removeUpdates(this);
            }
            locationManager = null;
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private void sendLocation2Server(){
        //http client to send data
    }

    private Map<String,String> getHeaderParams(String token) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("YOUR_KEY", token);
        return params;
    }

    private Map<String,String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("YOUR_KEY", "VALUE");
        return params;
    }

    private double calculateDistance(double GPSlat, double GPSlng, double userlat, double userlng){
        Location startPoint = new Location("locationA");
        startPoint.setLatitude(userlat);
        startPoint.setLongitude(userlng);

        Location endpoint = new Location("locationB");
        endpoint.setLatitude(GPSlat);
        endpoint.setLongitude(GPSlng);

        return endpoint.distanceTo(startPoint);
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartService = new Intent(getApplicationContext(), this.getClass());
        restartService.setPackage(getPackageName());
        PendingIntent restartServicePI = PendingIntent.getService(
                getApplicationContext(), 1, restartService,
                PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager)getApplicationContext()
                .getSystemService(Context.ALARM_SERVICE);
        if (alarmService != null) {
            alarmService.set(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime() +1000, restartServicePI);
        }
    }

    private void getCurrLocationName(){
        try {
            mAddress = mGeocoder.getFromLocation(userlat, userlng, 1);
            Log.d("mAddress tes1", mAddress.get(0).getAddressLine(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
