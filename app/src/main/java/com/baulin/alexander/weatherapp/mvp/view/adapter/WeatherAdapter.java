package com.baulin.alexander.weatherapp.mvp.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baulin.alexander.weatherapp.App;
import com.baulin.alexander.weatherapp.R;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.WeatherCityItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<WeatherCityItem> cities;

    public void setData(List<WeatherCityItem> cities) {
        this.cities = cities;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_weather, viewGroup, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherCityItem item = cities.get(position);

        holder.city.setText(item.name);

        String averageTemperature = String.valueOf(item.main.temp) + " °C";
        holder.temperature.setText(averageTemperature);

        //holder.temperature.setText(String.format(App.getStringFrom(R.string.average_temperature), item.main.temp));

        //String url = new StringBuilder("https://openweathermap.org/img/w/01d.png").toString();
        String url =
                "https://openweathermap.org/img/w/" +
                item.getWeather().get(0).icon +
                ".png";

        Picasso.get().load(url).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView city, temperature;
        ImageView image;

        WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.txtCityName);
            image = itemView.findViewById(R.id.imgWeather);
            temperature = itemView.findViewById(R.id.txtTemperature);
        }
    }
}
