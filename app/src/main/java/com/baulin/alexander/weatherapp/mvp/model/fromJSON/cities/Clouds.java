package com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

@AutoValue
public abstract class Clouds implements Parcelable {


    public abstract int today();
}
