package com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class RootWeatherCities {

    public abstract int cod();
    public abstract double calctime();
    public abstract int cnt();
    public abstract List<WeatherCityItem> list();

    public static TypeAdapter<RootWeatherCities> typeAdapter(Gson gson) {
        return new AutoValue_RootWeatherCities.GsonTypeAdapter(gson);
    }


}
