package com.baulin.alexander.weatherapp;

import android.Manifest;
import android.app.Activity;
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
        int availability = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext());
        return (availability == ConnectionResult.SUCCESS);

    }

    public static boolean isFineLocationPermissionGranted() {
        int permission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isCoarseLocationPermissionGranted() {
        int permission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static String getStringFrom(int stringRes) {
        return getContext().getResources().getString(stringRes);
    }






}
