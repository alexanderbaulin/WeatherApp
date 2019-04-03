package com.baulin.alexander.weatherapp.mvp.model.fromJSON.city;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class RootWeatherCity {
    public abstract Coord coord();
    public abstract List<Weather> weather();
    public abstract String base();
    public abstract Main main();
    public abstract int visibility();
    public abstract Wind wind();
    public abstract Clouds clouds();
    public abstract int dt();
    public abstract Sys sys();
    public abstract int id();
    public abstract String name();
    public abstract int cod();

    public static TypeAdapter<RootWeatherCity> typeAdapter(Gson gson) {
        return new AutoValue_RootWeatherCity.GsonTypeAdapter(gson);
    }
}
