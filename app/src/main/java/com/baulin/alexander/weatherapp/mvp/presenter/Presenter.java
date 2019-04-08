package com.baulin.alexander.weatherapp.mvp.presenter;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.LocaleDisplayNames;
import android.net.ConnectivityManager;
import android.util.Log;

import com.baulin.alexander.weatherapp.App;
import com.baulin.alexander.weatherapp.dagger2.components.AppComponent;
import com.baulin.alexander.weatherapp.mvp.interfaces.Model;
import com.baulin.alexander.weatherapp.mvp.interfaces.View;
import com.baulin.alexander.weatherapp.mvp.model.Data;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.RootWeatherCities;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;
import com.baulin.alexander.weatherapp.mvp.view.activities.Main;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.model.LatLngBounds;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Presenter implements com.baulin.alexander.weatherapp.mvp.interfaces.Presenter {

    @Inject
    Model data;
    private CompositeDisposable citiesWeatherRequests;
    private CompositeDisposable cityDetailWeatherRequests;

    private WeakReference<View> view;

    public Presenter() {
        AppComponent component = App.getComponent();
        if(component != null) component.injectPresenter(this);

        citiesWeatherRequests = new CompositeDisposable();
        cityDetailWeatherRequests = new CompositeDisposable();
    }

    public void setActivity(Main activity) {
        view = new WeakReference<View>(activity);

        setNetworkChangeReceiver();
    }

    @Override
    public void getCurrentCityWeather(String cityName) {
        cityDetailWeatherRequests.clear();
        cityDetailWeatherRequests.add(data.getCurrentCityWeather(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootWeatherCity>() {
                               @Override
                               public void accept(RootWeatherCity rootWeatherCity) throws Exception {
                                   Log.d("onClick", "getCurrentCity " + rootWeatherCity.name());
                                   view.get().display(rootWeatherCity);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   view.get().showMessage("Error: " + throwable.getMessage() + ". Check Internet connection");
                                   //Log.d("onClick", "error getCurrentCity " + throwable.getMessage());
                               }
                           }
                )
        );
    }

    @Override
    public void getCitiesWeather(LatLngBounds latLngBounds, float zoom) {
        citiesWeatherRequests.clear();
        citiesWeatherRequests.add(data.getCitiesWeather(latLngBounds, zoom)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootWeatherCities>() {
                    @Override
                    public void accept(RootWeatherCities rootWeatherObject) throws Exception {
                        view.get().hideCitiesSheet(false);
                        view.get().display(rootWeatherObject.list());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(throwable.getMessage().contains("Expected BEGIN_OBJECT"))
                            view.get().hideCitiesSheet(true);
                        else {
                            view.get().showMessage("Error: " + throwable.getMessage() + ". Check Internet connection");
                            Log.d("autovalue", "Error: " + throwable.getMessage());
                        }
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

    @Override
    public void onDestroyActivity() {
        citiesWeatherRequests.clear();
        cityDetailWeatherRequests.clear();
    }

    public class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(view.get() == null) return;
            if(App.haveNetworkConnection())
                view.get().setEmptyScreen(false);
            else {
                view.get().setEmptyScreen(true);
                view.get().showMessage("No internet connection");
            }
        }
    }

    private void setNetworkChangeReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        App.getContext().registerReceiver(new NetworkChangeReceiver(), filter);
    }
}
