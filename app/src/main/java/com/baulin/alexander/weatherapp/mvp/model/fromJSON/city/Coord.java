package com.baulin.alexander.weatherapp.mvp.model.fromJSON.city;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Coord {

    public abstract double lon();
    public abstract double lat();

    public static TypeAdapter<Coord> typeAdapter(Gson gson) {
        return new AutoValue_Coord.GsonTypeAdapter(gson);
    }

}
