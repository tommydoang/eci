package com.example.tomz.electroniccity.page.bn_tab_home.store;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.local.pref.AppPreferencesHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FragStore extends Fragment implements OnMapReadyCallback{

    AppPreferencesHelper mAppPreferencesHelper;
    ListView listView;
    SupportMapFragment mSupportMapFragment;
    GoogleMap map;

    public FragStore() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_store, container, false);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng currPos = new LatLng(Double.parseDouble(mAppPreferencesHelper.getUserLat()),
                Double.parseDouble(mAppPreferencesHelper.getUserLng()));
        map.addMarker(new MarkerOptions().position(currPos).title("WOY!!"));
        map.moveCamera(CameraUpdateFactory.newLatLng(currPos));

    }
}
