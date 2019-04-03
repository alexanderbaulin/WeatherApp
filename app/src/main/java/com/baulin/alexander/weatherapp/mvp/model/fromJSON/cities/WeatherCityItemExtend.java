package com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class WeatherCityItemExtend {

    public abstract int id();
    public abstract String main();
    public abstract String description();
    public abstract String icon();

    public static TypeAdapter<WeatherCityItemExtend> typeAdapter(Gson gson) {
        return new AutoValue_WeatherCityItemExtend.GsonTypeAdapter(gson);
    }


}
