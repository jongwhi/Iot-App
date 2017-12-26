package com.example.owner.myapplication;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

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

    // 클라우드에서 값을 받아오지 못해서 수동으로 좌표값 배열에 저장
    private ArrayList<LatLng> shuttle = new ArrayList<LatLng>(
            Arrays.asList(HansungUniversity, HansungUniversity, SuperFront, BeobHwaSa, SamGeoLi, TaxOffice,
                    SamSeonMarket, HansungStation, HansungStationExit2, GreenCrossPharmacy, SamYeongTang,
                    TaxOffice, SamGeoLi, BeobHwaSa, SuperFront, HansungUniversity));
    GoogleMap googleMap;
    MarkerOptions markerOptions;
    CameraUpdate cameraUpdate;
    CameraUpdate zoom;
    Marker marker;
    static int i = 0;
    // moveMarker 함수를 이용하여 marker 갱신
    public void onMapReady(final GoogleMap map) {
        googleMap = map;
        if (i>=15){
            moveMarker(map, shuttle.get(0));
        } else{
            moveMarker(map, shuttle.get(i));
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // markerHandler, markerRunnable, markerThread
        // -> 마커 갱신을 위한 thread -> 4초마다 갱신
        final Handler markerHandler = new Handler(){
            public void handleMessage(Message msg){
                MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.googlemap);
                mapFragment.getMapAsync(Map.this);
            }
        };
        Runnable markerRunnable = new Runnable() {
            @Override
            public void run() {
                while (i < 16) {
                    try {
                        Thread.sleep(4000);
                        Message msg = Message.obtain();
                        markerHandler.sendMessage(msg);
                        i++;
                    } catch (InterruptedException e) {
                        e.getStackTrace();
                    }
                }
            }
        };
        Thread markerThread = new Thread(markerRunnable);
        markerThread.start();
    }
    // 실질적으로 마커 갱신 해주는 함수
    public void moveMarker(final GoogleMap map, LatLng latLng){
        googleMap = map;
        googleMap.clear();
        cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
        googleMap.moveCamera(cameraUpdate);
        zoom = CameraUpdateFactory.zoomTo(16);
        googleMap.animateCamera(zoom);
        markerOptions = new MarkerOptions().position(latLng);
        marker = googleMap.addMarker(markerOptions);
        googleMap.addMarker(markerOptions).showInfoWindow();
    }
}