package com.baulin.alexander.weatherapp.mvp.view.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.baulin.alexander.weatherapp.App;
import com.baulin.alexander.weatherapp.R;

import com.baulin.alexander.weatherapp.Utils;
import com.baulin.alexander.weatherapp.dagger2.components.DaggerMainActivityComponent;
import com.baulin.alexander.weatherapp.dagger2.components.MainActivityComponent;
import com.baulin.alexander.weatherapp.mvp.interfaces.Presenter;
import com.baulin.alexander.weatherapp.mvp.interfaces.View;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.WeatherCityItem;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;

import com.baulin.alexander.weatherapp.mvp.view.adapter.WeatherAdapter;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Main extends AppCompatActivity implements OnMapReadyCallback, View, WeatherAdapter.OnItemClickListener {

    @Inject
    Presenter presenter;
    GoogleMap map;
    @BindView(R.id.recView)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fixScreenOrientation(true);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .appComponent(App.getComponent())
                .build();

        component.injectMainActivity(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        SupportMapFragment  fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (fragment != null) {
            fragment.getMapAsync(this);
        }

        presenter.setActivity(this);

        if(savedInstanceState == null) {
            Log.d("savedInstanceState", "savedInstanceState = null");
            LocationCallback mLocationCallback = createCallback();
            presenter.getDeviceLocation(mLocationCallback);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                fixScreenOrientation(true);

                Log.d("camera", "onCameraIdle " + map.getCameraPosition().target + "zoom " + map.getCameraPosition().zoom);
                Log.d("camera", "visible bounds " + map.getProjection().getVisibleRegion().latLngBounds);

                LatLngBounds bounds = map.getProjection().getVisibleRegion().latLngBounds;
                float zoom = map.getCameraPosition().zoom;

                presenter.getCitiesWeather(bounds, zoom);
            }

        });

        if (!isFineLocationPermissionGranted()) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, App.REQUEST_CODE_FINE_LOCATION);
        } else
            map.setMyLocationEnabled(true);

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case App.REQUEST_CODE_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("savedInstanceState", "Location granted");
                    map.setMyLocationEnabled(true);
                    LocationCallback mLocationCallback = createCallback();
                    presenter.getDeviceLocation(mLocationCallback);
                    Log.d("permission", "granted");
                } else {
                    map.setMyLocationEnabled(false);
                    Log.d("permission", "denied");
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroyActivity();
        super.onDestroy();
    }

    @Override
    public void display(List<WeatherCityItem> list) {
        Log.d("display", "-------------------");
        Log.d("display", "size = " + list.size());
        for(int i = 0; i < list.size(); i++) {
            Log.d("display", "city name = " + list.get(i).name());
            Log.d("display", "clouds = " + list.get(i).clouds().all());
            Log.d("display", "city id = " + list.get(i).id() + " " + list.get(i).weather().get(0).id());
        }
        WeatherAdapter adapter = new WeatherAdapter();
        adapter.setData(list);
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);

        fixScreenOrientation(false);
    }

    @Override
    public void display(RootWeatherCity rootWeatherCity) {
        Log.d("onClick", "display " + rootWeatherCity.name() + " t = " + rootWeatherCity.main().temp());

        Intent intent = new Intent(this, DetailCityWeather.class);

        intent.putExtra(App.CITY_NAME, rootWeatherCity.name());
        intent.putExtra(App.NAME_MAIN_CLASS, rootWeatherCity.main());
        intent.putExtra(App.NAME_SYS_CLASS, rootWeatherCity.sys());
        intent.putExtra(App.NAME_WEATHER_CLASS, rootWeatherCity.weather().get(0));
        intent.putExtra(App.NAME_WIND_CLASS, rootWeatherCity.wind());

        startActivity(intent);

        fixScreenOrientation(false);

    }

    @Override
    public void onItemClick(String cityName) {
        Log.d("click", "click + " + cityName);
        presenter.getCurrentCityWeather(cityName);
    }

    @Override
    public void setEmptyScreen(boolean setEmpty) {
        SupportMapFragment  fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if(fragment == null || fragment.getView() == null) return;
        if(setEmpty) {
            fragment.getView().setVisibility(android.view.View.INVISIBLE);
            recyclerView.setVisibility(android.view.View.INVISIBLE);
        } else {
            fragment.getView().setVisibility(android.view.View.VISIBLE);
            recyclerView.setVisibility(android.view.View.VISIBLE);
        }
    }

    @Override
    public void hideCitiesSheet(boolean setHidden) {
        if(setHidden)
            recyclerView.setVisibility(android.view.View.GONE);
        else
            recyclerView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private LocationCallback createCallback() {
        return new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Location location = locationResult.getLastLocation();
                presenter.stopDeviceLocationTracking();
                LatLng coordinates = new LatLng(location.getLatitude(), location.getLongitude());
                CameraUpdate position = CameraUpdateFactory.newLatLngZoom(coordinates , App.DEFAULT_ZOOM);
                map.moveCamera(position);

            }
        };
    }

    public boolean isFineLocationPermissionGranted() {
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public GoogleMap getMap() {
        return map;
    }

    public WeatherAdapter getWeatherAdapter() {
        return (WeatherAdapter) recyclerView.getAdapter();
    }

    public void fixScreenOrientation(boolean fixScreen) {
        if(fixScreen) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
    }
}
