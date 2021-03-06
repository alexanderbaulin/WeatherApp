package com.baulin.alexander.weatherapp.mvp.model;


import android.annotation.SuppressLint;
import android.os.Looper;

import com.baulin.alexander.weatherapp.App;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


public class DeviceLocation {
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;

    public DeviceLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(App.getContext());
    }

    @SuppressLint("MissingPermission")
    public void setLocationCallback(LocationCallback locationCallback) {
        mLocationCallback = locationCallback;
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    public void removeLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }
}
