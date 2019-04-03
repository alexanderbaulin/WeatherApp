package com.baulin.alexander.weatherapp.mvp.model.fromJSON.city;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Wind implements Parcelable {

    public abstract double speed();
    public abstract double deg();

    public static TypeAdapter<Wind> typeAdapter(Gson gson) {
        return new AutoValue_Wind.GsonTypeAdapter(gson);
    }

}
