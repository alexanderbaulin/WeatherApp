package com.baulin.alexander.weatherapp.mvp.view.activities;

import android.os.Parcelable;



import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.Main;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.Sys;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.Weather;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.Wind;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;


@AutoValue
public abstract class DetailCityWeatherDTO implements Parcelable {

    public abstract String name();
    public abstract Main main();
    public abstract Sys sys();
    public abstract Weather weather();
    public abstract Wind wind();

}
