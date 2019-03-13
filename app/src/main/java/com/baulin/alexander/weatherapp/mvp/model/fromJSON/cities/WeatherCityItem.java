package com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities;

import java.util.List;

public class WeatherCityItem {

    public int id;
    public int dt;
    public String name;
    public Coord coord;
    public Main main;
    public Wind wind;
    public Rain rain;
    public Snow snow;
    public Clouds clouds;
    public List<WeatherCityItemExtend> weatherCityItemExtend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public List<WeatherCityItemExtend> getWeatherCityItemExtend() {
        return weatherCityItemExtend;
    }

    public void setWeatherCityItemExtend(List<WeatherCityItemExtend> weatherCityItemExtend) {
        this.weatherCityItemExtend = weatherCityItemExtend;
    }
}
