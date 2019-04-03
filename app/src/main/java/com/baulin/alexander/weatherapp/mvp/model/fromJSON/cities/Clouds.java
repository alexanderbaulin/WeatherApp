package com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities;



import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;


@AutoValue
public abstract class Clouds {
    public abstract int all();

    public static TypeAdapter<Clouds> typeAdapter(Gson gson) {
        return new AutoValue_Clouds.GsonTypeAdapter(gson);
    }
}
