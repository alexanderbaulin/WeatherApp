package com.baulin.alexander.weatherapp.mvp.model;

import com.baulin.alexander.weatherapp.App;
import com.baulin.alexander.weatherapp.dagger2.components.AppComponent;
import com.baulin.alexander.weatherapp.mvp.interfaces.Model;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.RootWeatherCities;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;
import com.baulin.alexander.weatherapp.mvp.model.retrofit.RetrofitAPI;
import com.baulin.alexander.weatherapp.mvp.model.retrofit.RetrofitClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.model.LatLngBounds;

import javax.inject.Inject;

import io.reactivex.Observable;

public class Data implements Model {

    @Inject
    RetrofitAPI client;

    @Inject
    DeviceLocation deviceLocation;

    public Data() {
        AppComponent component = App.getAppComponent();
        if(component != null) component.injectData(this);
    }

    @Override
    public Observable<RootWeatherCities> getCitiesWeather(LatLngBounds latLngBounds, float zoom) {

        double lon_left = latLngBounds.southwest.longitude;
        double lat_bottom = latLngBounds.southwest.latitude;
        double lon_right = latLngBounds.northeast.longitude;
        double lat_top = latLngBounds.northeast.latitude;

        String request =
                String.valueOf(lon_left) + "," +
                String.valueOf(lat_bottom) + "," +
                String.valueOf(lon_right) + "," +
                String.valueOf(lat_top) + "," +
                String.valueOf((int) zoom);

        return client.getCitiesWeatherFromJSON(request, "metric", RetrofitClient.API_key);
    }

    @Override
    public Observable<RootWeatherCity> getCurrentCityWeather(String cityName) {
        return client.getCityWeatherFromJSON(cityName, "metric", RetrofitClient.API_key);
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
