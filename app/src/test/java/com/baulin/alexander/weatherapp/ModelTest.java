package com.baulin.alexander.weatherapp;


import com.baulin.alexander.weatherapp.mvp.model.Data;
import com.baulin.alexander.weatherapp.mvp.model.DeviceLocation;
import com.baulin.alexander.weatherapp.mvp.model.retrofit.RetrofitAPI;
import com.baulin.alexander.weatherapp.mvp.model.retrofit.RetrofitClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ModelTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private DeviceLocation deviceLocation;

    @Mock
    private RetrofitAPI client;

    @InjectMocks
    private Data data;


    @Test
    public void testStopDeviceTracking() {
        data.stopDeviceLocationTracking();
        verify(deviceLocation).removeLocationUpdates();
    }

    @Test
    public void testCitiesRequests() {
        LatLngBounds bounds = new LatLngBounds(new LatLng(1, 1), new LatLng(2, 2));
        float zoom = 1;
        String request = "1.0,1.0,2.0,2.0,1";

        data.getCitiesWeather(bounds, zoom);
        verify(client).getCitiesWeatherFromJSON(request, "metric", RetrofitClient.API_key);
    }

    @Test
    public void testCityRequest() {
        data.getCurrentCityWeather("cityName");
        verify(client).getCityWeatherFromJSON("cityName", "metric", RetrofitClient.API_key);
    }

    @Test
    public void testSetCallback() {
        LocationCallback callback = ArgumentMatchers.any(LocationCallback.class);
        data.getDeviceLocation(callback);
        verify(deviceLocation).setLocationCallback(callback);
    }

}
