package com.baulin.alexander.weatherapp.mvp.presenter;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkCapabilities;
import android.util.Log;

import com.baulin.alexander.weatherapp.mvp.interfaces.Model;
import com.baulin.alexander.weatherapp.mvp.interfaces.View;
import com.baulin.alexander.weatherapp.mvp.model.Data;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.RootWeatherCities;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;
import com.baulin.alexander.weatherapp.mvp.view.activities.Main;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.model.LatLngBounds;

import java.lang.ref.WeakReference;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Presenter implements com.baulin.alexander.weatherapp.mvp.interfaces.Presenter {

    Model data = new Data();
    private CompositeDisposable compositeDisposable;

    private WeakReference<View> view;

    public void setActivity(Main activity) {
        view = new WeakReference<View>(activity);
    }

    @Override
    public void getCurrentCityWeather(String cityName) {
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(data.getCurrentCityWeather(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootWeatherCity>() {
                               @Override
                               public void accept(RootWeatherCity rootWeatherCity) throws Exception {
                                   Log.d("onClick", "getCurrentCity " + rootWeatherCity.name);
                                   view.get().display(rootWeatherCity);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }

                )

        );
    }

    @Override
    public void getCitiesWeather(LatLngBounds latLngBounds, float zoom) {
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(data.getCitiesWeather(latLngBounds, zoom)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootWeatherCities>() {
                    @Override
                    public void accept(RootWeatherCities rootWeatherObject) throws Exception {
                        view.get().display(rootWeatherObject.list);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("myLogs", "Error: " + throwable.getMessage() + ". Check Internet connection");
                    }
                })
        );
    }

    @Override
    public void getDeviceLocation(LocationCallback locationCallback) {
        data.getDeviceLocation(locationCallback);
    }

    @Override
    public void stopDeviceLocationTracking() {
        data.stopDeviceLocationTracking();
    }

    public class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
