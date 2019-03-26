package com.baulin.alexander.weatherapp.mvp.model.retrofit;

import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.RootWeatherCities;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("data/2.5/box/city")
    Observable<RootWeatherCities> getCitiesWeatherFromJSON(@Query("bbox") String request, @Query("units") String unites, @Query("appid") String api);

    @GET("data/2.5/weather")
    Observable<RootWeatherCity> getCityWeatherFromJSON(@Query("q") String cityName, @Query("units") String unites, @Query("appid") String api);

}
