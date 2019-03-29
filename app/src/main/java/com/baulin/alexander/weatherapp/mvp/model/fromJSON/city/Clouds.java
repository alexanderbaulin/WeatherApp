package com.baulin.alexander.weatherapp.mvp.model.fromJSON.city;

//import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

//@AutoValue
public class Clouds {
    public int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    /*
    public abstract int all();

    public static TypeAdapter<Clouds> typeAdapter(Gson gson) {
        return new AutoValue_Clouds.GsonTypeAdapter(gson);
    }
       */
}
