package com.example.owner.myapplication;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ddoch on 2017-12-14.
 */

public class MapThread extends Thread implements Runnable {
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
    MarkerOptions markerOptions;
    CameraUpdate cameraUpdate;
    CameraUpdate zoom;

    public void run() {
        int i = 1;
        while (true) {
            i++;
            try {
                Thread.sleep(1000);
                cameraUpdate = CameraUpdateFactory.newLatLng(shuttle.get(i));
                Log.e("checkLang", shuttle.get(i).latitude + " + " + shuttle.get(i).longitude);
                googleMap.moveCamera(cameraUpdate);
                //googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(latLng.latitude,latLng.longitude));
                zoom = CameraUpdateFactory.zoomTo(18);
                googleMap.animateCamera(zoom);
                markerOptions = new MarkerOptions().position(shuttle.get(i));
                googleMap.addMarker(markerOptions);
                i++;
                Log.e("asds", "asd");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}