package com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class WeatherCityItem {

    public abstract int id();
    public abstract int dt();
    public abstract String name();
    public abstract Coord coord();
    public abstract Main main();
    public abstract Wind wind();
    public abstract Clouds clouds();
    public abstract List<WeatherCityItemExtend> weather();

    public static TypeAdapter<WeatherCityItem> typeAdapter(Gson gson) {
        return new AutoValue_WeatherCityItem.GsonTypeAdapter(gson);
    }


}
