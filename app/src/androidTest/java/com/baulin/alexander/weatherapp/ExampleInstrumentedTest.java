package com.baulin.alexander.weatherapp;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.net.wifi.WifiManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.baulin.alexander.weatherapp.mvp.view.activities.Main;
import com.baulin.alexander.weatherapp.mvp.view.adapter.WeatherAdapter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static org.junit.Assert.*;

/**
 * Instrumented TestMap, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public final ActivityRule<Main> main = new ActivityRule<>(Main.class);


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.baulin.alexander.weatherapp", appContext.getPackageName());
    }

    @Test
    public void TestMap() {

        RecyclerView recyclerView = main.get().findViewById(R.id.recView);
        assertNotNull(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        assertNotNull(layoutManager);

        while (true) {
            if(layoutManager.getItemCount() != 0) break;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertNotNull(adapter);

        GoogleMap map = main.get().getMap();
        LatLng coordinates = new LatLng(50.43, 30.52);
        CameraUpdate position = CameraUpdateFactory.newLatLngZoom(coordinates , App.DEFAULT_ZOOM);

        main.get().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                map.moveCamera(position);
            }
        });

        while (true) {
            WeatherAdapter weatherAdapter = main.get().getWeatherAdapter();
            boolean testCityFound = false;
            for(int i = 0; i < weatherAdapter.getItemCount(); i++) {
                String city = weatherAdapter.getItem(i).name();
                if(city.equals("Kiev")) testCityFound = true;
            }
            if(testCityFound) break;
        }
        onView(ViewMatchers.withId(R.id.recView)).perform(RecyclerViewActions.actionOnItemAtPosition(main.get().getWeatherAdapter().getItemCount()-1, click()));

    }

    @Test
    public void testOffWifiConnection() {

        RecyclerView recyclerView = main.get().findViewById(R.id.recView);
        assertNotNull(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        assertNotNull(layoutManager);

        while (true) {
            if(layoutManager.getItemCount() != 0) break;
        }
        WifiManager wifiManager = (WifiManager) main.get().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);

        wifiManager.setWifiEnabled(true);

        while (true) {
            if(recyclerView.getVisibility() == View.VISIBLE) break;
        }
        onView(ViewMatchers.withId(R.id.recView)).perform(RecyclerViewActions.actionOnItemAtPosition(main.get().getWeatherAdapter().getItemCount()-1, click()));

    }

}
