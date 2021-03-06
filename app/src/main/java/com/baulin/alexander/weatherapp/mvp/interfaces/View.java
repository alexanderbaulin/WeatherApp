package com.baulin.alexander.weatherapp.mvp.interfaces;

import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.WeatherCityItem;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;

import java.util.List;

public interface View {
    void display(List<WeatherCityItem> list);
    void display(RootWeatherCity rootWeatherCity);
    void setEmptyScreen(boolean setEmpty);
    void hideCitiesSheet(boolean setHidden);
    void showMessage(String message);
    void fixScreenOrientation(boolean fixScreen);
}
