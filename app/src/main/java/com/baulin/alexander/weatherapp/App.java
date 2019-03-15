package com.baulin.alexander.weatherapp;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class App extends Application {
    private static App instance;

    public static boolean isGooglePlayServiceAvailable() {
        int availability = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(instance.getApplicationContext());
        return (availability == ConnectionResult.SUCCESS);

    }

    public static boolean isLocationPermissionGranted() {
        int permission = ContextCompat.checkSelfPermission(instance.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }




}
