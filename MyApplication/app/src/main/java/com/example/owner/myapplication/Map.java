package com.example.owner.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;

public class Map extends AppCompatActivity implements OnMapReadyCallback {
    static final LatLng HansungUniversity = new LatLng(37.582428, 127.011291);
    static final LatLng SuperFront = new LatLng(37.583514, 127.011087);
    static final LatLng BeobHwaSa = new LatLng(37.584562, 127.011026);
    static final LatLng SamGeoLi = new LatLng(37.586273, 127.010875);
    static final LatLng TaxOffice = new LatLng(37.587979, 127.010477);
    static final LatLng SamSeonMarket = new LatLng(37.588034, 127.008922);
    static final LatLng HansungStation = new LatLng(37.588026, 127.006703);
    static final LatLng HansungStationExit2 = new LatLng(37.588602, 127.006791);
    static final LatLng GreenCrossPharmacy = new LatLng(37.589737, 127.009385);
    static final LatLng SamYeongTang = new LatLng(37.588798, 127.010118);

    private ArrayList<LatLng> shuttle = new ArrayList<LatLng>(
            Arrays.asList(HansungUniversity, SuperFront, BeobHwaSa, SamGeoLi, TaxOffice,
                    SamSeonMarket, HansungStation, HansungStationExit2, GreenCrossPharmacy, SamYeongTang,
                    TaxOffice, SamGeoLi, BeobHwaSa, SuperFront, HansungUniversity));
    GoogleMap googleMap;


    public void onMapReady(final GoogleMap map) {
        googleMap = map;
        MapThread mapThread = new MapThread();
        mapThread.run();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.googlemap);
        mapFragment.getMapAsync(this);
    }
}