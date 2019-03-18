package com.baulin.alexander.weatherapp.mvp.view.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baulin.alexander.weatherapp.App;
import com.baulin.alexander.weatherapp.R;
import com.baulin.alexander.weatherapp.mvp.presenter.Presenter;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


public class Main extends AppCompatActivity implements OnMapReadyCallback {


    Presenter presenter;
    GoogleMap map;

    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter();
       // presenter.test();

        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (fragment != null) {
            fragment.getMapAsync(this);
        }

        if (App.isGooglePlayServiceAvailable()) Log.d("myLogs", "ok");
        else Log.d("myLogs", "not ok");

        if(App.isFineLocationPermissionGranted())
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if(App.isCoarseLocationPermissionGranted())
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);


        LocationCallback mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                location = locationResult.getLastLocation();
                presenter.stopDeviceLocationTracking();
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12));
            }
        };
        presenter.getDeviceLocation(mLocationCallback);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                Log.d("camera", "onCameraIdle " + map.getCameraPosition().target + "zoom " + map.getCameraPosition().zoom);
                Log.d("camera", "visible bounds " + map.getProjection().getVisibleRegion().latLngBounds);
                presenter.testWeatherCities(map.getProjection().getVisibleRegion().latLngBounds, map.getCameraPosition().zoom);
            }

        });
        map.setMyLocationEnabled(true);
    }
}
