package com.baulin.alexander.weatherapp.mvp.model.retrofit;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String API_key = "10ffe9e6913b2ac1529992c5618ca106";
    private static Retrofit instance;

    public static Retrofit getInstance() {

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
                new GsonBuilder()
                        .registerTypeAdapterFactory(AutoValueGsonFactory.create())
                        .create()
        );

        if(instance == null) instance = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return instance;
    }

    private RetrofitClient() {
    }
}
