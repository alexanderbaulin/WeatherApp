package com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities;

import java.util.List;

public class RootWeatherCities {

    public int cod;
    public double calctime;
    public int cnt;
    public List<WeatherCityItem> list;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getCalctime() {
        return calctime;
    }

    public void setCalctime(double calctime) {
        this.calctime = calctime;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherCityItem> getList() {
        return list;
    }

    public void setList(List<WeatherCityItem> list) {
        this.list = list;
    }
}
