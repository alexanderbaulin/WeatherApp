package com.baulin.alexander.weatherapp.mvp.model.fromJSON.city;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Weather implements Parcelable {

    public abstract int id();
    public abstract String main();
    public abstract String description();
    public abstract String icon();

    public static TypeAdapter<Weather> typeAdapter(Gson gson) {
        return new AutoValue_Weather.GsonTypeAdapter(gson);
    }

}
