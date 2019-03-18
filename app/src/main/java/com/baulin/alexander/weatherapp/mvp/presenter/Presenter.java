package com.baulin.alexander.weatherapp.mvp.presenter;


import android.util.Log;

import com.baulin.alexander.weatherapp.mvp.interfaces.Model;
import com.baulin.alexander.weatherapp.mvp.model.Data;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.RootWeatherCities;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.model.LatLngBounds;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Presenter implements com.baulin.alexander.weatherapp.mvp.interfaces.Presenter {

    Model data = new Data();
    private CompositeDisposable compositeDisposable;


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
                .subscribe(new Consumer<RootWeatherCity>() {
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


    @Override
    public void testWeatherCities(LatLngBounds latLngBounds, float zoom) {
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(data.getCitiesWeather(latLngBounds.southwest.longitude,latLngBounds.southwest.latitude,latLngBounds.northeast.longitude,latLngBounds.northeast.latitude, zoom)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootWeatherCities>() {
                    @Override
                    public void accept(RootWeatherCities rootWeatherObject) throws Exception {
                        Log.d("myLogs", "-------------------");
                        for(int i = 0; i < rootWeatherObject.list.size(); i++) {
                            Log.d("myLogs", "wind speed = " + rootWeatherObject.list.get(i).name);
                        }
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
}
