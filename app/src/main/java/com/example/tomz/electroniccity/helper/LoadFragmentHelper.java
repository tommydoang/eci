package com.example.tomz.electroniccity.helper;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.tomz.electroniccity.R;

public class LoadFragmentHelper {

    public void loadFragment(Activity activity, int containerID, Fragment fragment){
        FragmentManager fm = ((FragmentActivity)activity).getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerID, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void loadFragment(Activity activity, int containerID, Fragment fragment, String tag){
        FragmentManager fm = ((FragmentActivity)activity).getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerID, fragment, tag);
        ft.addToBackStack(null);
        ft.commit();
    }


}
