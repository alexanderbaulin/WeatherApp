package com.baulin.alexander.weatherapp.mvp.model.fromJSON.city;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Sys implements Parcelable {

    public abstract int type();
    public abstract int id();
    public abstract double message();
    public abstract String country();
    public abstract int sunrise();
    public abstract int sunset();

    public static TypeAdapter<Sys> typeAdapter(Gson gson) {
        return new AutoValue_Sys.GsonTypeAdapter(gson);
    }
}
