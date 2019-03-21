package com.baulin.alexander.weatherapp.mvp.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baulin.alexander.weatherapp.App;
import com.baulin.alexander.weatherapp.R;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.WeatherCityItem;
import com.baulin.alexander.weatherapp.mvp.view.activities.Main;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<WeatherCityItem> cities;
    private OnItemClickListener onClickListener;

    public interface OnItemClickListener {
        void onItemClick(String cityName);
    }

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
    public void onBindViewHolder(@NonNull final WeatherViewHolder holder, final int position) {
        final WeatherCityItem item = cities.get(position);

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("click", "onClick");
                String cityName = item.name;
                onClickListener.onItemClick(cityName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
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
