package com.baulin.alexander.weatherapp.mvp.model;

import android.util.Log;

import com.baulin.alexander.weatherapp.mvp.interfaces.Model;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.RootWeatherCities;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;
import com.baulin.alexander.weatherapp.mvp.model.retrofit.RetrofitAPI;
import com.baulin.alexander.weatherapp.mvp.model.retrofit.RetrofitClient;
import com.google.android.gms.location.LocationCallback;

import io.reactivex.Observable;

public class Data implements Model {
    private RetrofitAPI client = RetrofitClient.getInstance().create(RetrofitAPI.class);
    private DeviceLocation deviceLocation = new DeviceLocation();

    @Override
    public Observable<RootWeatherCities> getCitiesWeather(double lon_left, double lat_bottom, double lon_right, double lat_top, float zoom) {

        String coordinates = String.valueOf(lon_left) + "," +
                String.valueOf(lat_bottom) + "," +
                String.valueOf(lon_right) + "," +
                String.valueOf(lat_top) + "," +
                String.valueOf((int) zoom);

        return client.getCitiesWeatherFromJSON(coordinates, RetrofitClient.API_key);
    }

    @Override
    public Observable<RootWeatherCity> getCurrentCityWeather() {
        return client.getCityWeatherFromJSON("Kiev", RetrofitClient.API_key);
    }

    @Override
    public void getDeviceLocation(LocationCallback locationCallback) {
        deviceLocation.setLocationCallback(locationCallback);

    }

    @Override
    public void stopDeviceLocationTracking() {
        deviceLocation.removeLocationUpdates();
    }
}
