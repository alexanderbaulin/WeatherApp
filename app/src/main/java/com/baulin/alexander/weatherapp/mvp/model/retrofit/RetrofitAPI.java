package com.baulin.alexander.weatherapp.mvp.model.retrofit;

import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.RootWeatherCities;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("data/2.5/box/city?bbox=24,48,36,52,17&appid=10ffe9e6913b2ac1529992c5618ca106")
    Observable<RootWeatherCities> getCitiesWeatherFromJSON();

    @GET("data/2.5/weather?q=Kiev&appid=10ffe9e6913b2ac1529992c5618ca106")
    Observable<RootWeatherCity> getCityWeatherFromJSON();

}
