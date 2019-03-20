package com.baulin.alexander.weatherapp.mvp.interfaces;

import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.WeatherCityItem;

import java.util.List;

public interface View {
    void display(List<WeatherCityItem> list);
}
