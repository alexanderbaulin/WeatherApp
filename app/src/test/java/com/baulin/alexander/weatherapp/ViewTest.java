package com.baulin.alexander.weatherapp;

import com.baulin.alexander.weatherapp.mvp.interfaces.Presenter;
import com.baulin.alexander.weatherapp.mvp.view.activities.Main;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ViewTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Presenter presenter;
    @Mock
    private GoogleMap map;
    @InjectMocks
    private Main activity;

    @Test
    public void testOnItemClick() {
        activity.onItemClick("testName");
        verify(presenter).getCurrentCityWeather("testName");
    }

    @Test
    public void testSetOnCameraListener() {
        activity.onMapReady(map);
        verify(map).setOnCameraIdleListener(ArgumentMatchers.any(GoogleMap.OnCameraIdleListener.class));
    }

}
