package com.example.zguest.mapandbox;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.MapView;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public GoogleMap mMap;
    double mLat,mLon;
    public MapView mapView;
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this,"pk.eyJ1Ijoic2FuZGVlcC0yNiIsImEiOiJjamtzNW0xYjYxdjA3M290ZDE0dmJ4NWZoIn0.3mUVFilHHpO4isSWcfFytg");

        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Log.e("to","mapsAsync");

        Bundle data= getIntent().getExtras().getBundle("values");
        mLat= data.getDouble("lat");
        mLon= data.getDouble("lon");
        Log.e("lat in maps","lat "+mLat);
        Log.e("lon in maps","lon "+mLon);


        mapView=(MapView)findViewById(R.id.mapbox);
        Log.e("bundle", ""+savedInstanceState);
        mapView.onCreate(savedInstanceState);

        mapFragment.getMapAsync(this);

        //assert mapView != null;
        mapView.getMapAsync(new com.mapbox.mapboxsdk.maps.OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                com.mapbox.mapboxsdk.geometry.LatLng point = new com.mapbox.mapboxsdk.geometry.LatLng(mLat, mLon);
                Log.e("onMapbox","lat "+mLat);
                Log.e("onMapbox","lon "+mLon);
                Log.e("onMapbox","point "+point);
                mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                        .position(point)
                        .title("Marker"));
                mapboxMap.moveCamera(com.mapbox.mapboxsdk.camera.CameraUpdateFactory.newLatLng(point));
            }
        });
        //assert mapFragment != null;


    }

@Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.e("to google maps","maps");
        Log.e("lat in google maps","lat "+mLat);
        Log.e("lon in google maps","lon "+mLon);
        LatLng point = new LatLng(mLat, mLon);
        mMap.addMarker(new MarkerOptions()
                .position(point)
                .title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 18));

    }



//    public void onMapReady(MapboxMap mapboxMap) {
//        com.mapbox.mapboxsdk.geometry.LatLng point = new com.mapbox.mapboxsdk.geometry.LatLng(mLat, mLon);
//        Log.e("onMapbox","lat "+mLat);
//        Log.e("onMapbox","lon "+mLon);
//        Log.e("onMapbox","point "+point);
//        mapboxMap.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
//                .position(point)
//                .title("Marker"));
//        mapboxMap.moveCamera(com.mapbox.mapboxsdk.camera.CameraUpdateFactory.newLatLng(point));
//    }
}
