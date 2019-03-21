package com.baulin.alexander.weatherapp.mvp.presenter;


import android.app.Activity;
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
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }

                )

        );
    }

    /*
    public void test() {
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(data.getCitiesWeather(24.0,48.0,36.0,52.0,17)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootWeatherCities>() {
                    @Override
                    public void accept(RootWeatherCities rootWeatherObject) throws Exception {
                        Log.d("myLogs", "-------------------");
                        Log.d("myLogs", "wind speed = " + rootWeatherObject.list.get(0).name);
                        Log.d("myLogs", "wind speed = " + rootWeatherObject.list.get(1).name);
                        Log.d("myLogs", "wind speed = " + rootWeatherObject.list.get(2).name);
                        Log.d("myLogs", "wind speed = " + rootWeatherObject.list.get(3).name);
                        Log.d("myLogs", "wind speed = " + rootWeatherObject.list.get(4).name);
                        Log.d("myLogs", "wind speed = " + rootWeatherObject.list.get(5).name);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("myLogs", "Error: " + throwable.getMessage() + ". Check Internet connection");
                    }
                })
        );

        compositeDisposable.add(data.getCurrentCityWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootWeatherCity>()
                           {
                               @Override
                               public void accept(RootWeatherCity rootWeatherCity) throws Exception {
                                   Log.d("myLogs", "kiev = " + rootWeatherCity.name);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("myLogs", "Error: " + throwable.getMessage() + ". Check Internet connection");
                               }
                           }
                )
        );

    }
    */


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
        /*
        compositeDisposable.add(data.getCurrentCityWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootWeatherCity>()
                           {
                               @Override
                               public void accept(RootWeatherCity rootWeatherCity) throws Exception {
                                   Log.d("town", "--------  drody = " + rootWeatherCity.name);
                                   Log.d("town", "lat " + rootWeatherCity.coord.lat + " lon " + rootWeatherCity.coord.lon);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.d("town", "Error: " + throwable.getMessage() + ". Check Internet connection");
                               }
                           }
                )
        );
        */
    }

    @Override
    public void getDeviceLocation(LocationCallback locationCallback) {
        data.getDeviceLocation(locationCallback);
    }

    @Override
    public void stopDeviceLocationTracking() {
        data.stopDeviceLocationTracking();
    }
}
