package com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities;


import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Main {

    public abstract double temp();
    public abstract double temp_min();
    public abstract double temp_max();
    public abstract double pressure();
    public abstract int humidity();
    public abstract double sea_level();
    public abstract double grnd_level();

    public static TypeAdapter<Main> typeAdapter(Gson gson) {
        return new AutoValue_Main.GsonTypeAdapter(gson);
    }

}
