package com.baulin.alexander.weatherapp;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;

import com.baulin.alexander.weatherapp.dagger2.components.AppComponent;
import com.baulin.alexander.weatherapp.dagger2.components.DaggerAppComponent;
import com.baulin.alexander.weatherapp.dagger2.modules.AppModule;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.Main;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.Sys;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.Weather;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.Wind;
import com.baulin.alexander.weatherapp.mvp.model.retrofit.AutoValueGsonFactory;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    public static final int REQUEST_CODE_FINE_LOCATION = 1;
    public static final int DEFAULT_ZOOM = 12;

    public static final String NAME_MAIN_CLASS = Main.class.getCanonicalName();
    public static final String NAME_SYS_CLASS = Sys.class.getCanonicalName();
    public static final String NAME_WEATHER_CLASS = Weather.class.getCanonicalName();
    public static final String NAME_WIND_CLASS = Wind.class.getCanonicalName();
    public static final String CITY_NAME = "cityName";

    private static App instance;
    private static ConnectivityManager cm;

    private static AppComponent component;

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    public static String convertUnixToHour(int unites) {
        Date date = new Date(unites * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }

    public static boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }

        return haveConnectedWifi || haveConnectedMobile;
    }



}
