package com.baulin.alexander.weatherapp;


import android.database.Observable;

import com.baulin.alexander.weatherapp.mvp.model.Data;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.RootWeatherCities;
import com.baulin.alexander.weatherapp.mvp.presenter.Presenter;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;

import org.junit.Before;
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



import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;



import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Data data;



    @InjectMocks
    private Presenter presenter;


    @Test
    public void testStopDeviceTracking() {
        presenter.stopDeviceLocationTracking();
        verify(data).stopDeviceLocationTracking();
    }

    @Test
    public void testDeviceLocation() {
        LocationCallback callback = ArgumentMatchers.any(LocationCallback.class);
        presenter.getDeviceLocation(callback);
        verify(data).getDeviceLocation(callback);
    }


}
