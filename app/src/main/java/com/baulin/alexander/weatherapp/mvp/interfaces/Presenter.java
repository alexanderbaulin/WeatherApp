package com.baulin.alexander.weatherapp.mvp.interfaces;

import android.app.Activity;

import com.baulin.alexander.weatherapp.mvp.view.activities.Main;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.model.LatLngBounds;

public interface Presenter {
    void getCitiesWeather(LatLngBounds bounds, float zoom);
    void getDeviceLocation(LocationCallback locationCallback);
    void stopDeviceLocationTracking();
    void setActivity(Main activity);

    void getCurrentCityWeather(String cityName);

    void onDestroyActivity();
}
