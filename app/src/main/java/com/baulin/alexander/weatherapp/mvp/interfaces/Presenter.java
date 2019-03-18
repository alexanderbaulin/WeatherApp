package com.baulin.alexander.weatherapp.mvp.interfaces;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.model.LatLngBounds;

public interface Presenter {
    void testWeatherCities(LatLngBounds latLngBounds, float zoom);
    void getDeviceLocation(LocationCallback locationCallback);
    void stopDeviceLocationTracking();
}
