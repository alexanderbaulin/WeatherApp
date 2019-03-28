package com.baulin.alexander.weatherapp.mvp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baulin.alexander.weatherapp.App;
import com.baulin.alexander.weatherapp.R;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.Main;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.Sys;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.Weather;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.Wind;
import com.squareup.picasso.Picasso;

public class DetailCityWeather extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_current);


        Intent intent = getIntent();

        String name = intent.getStringExtra(App.CITY_NAME);
        Main main = intent.getParcelableExtra(App.NAME_MAIN_CLASS);
        Sys sys = intent.getParcelableExtra(App.NAME_SYS_CLASS);
        Weather weather = intent.getParcelableExtra(App.NAME_WEATHER_CLASS);
        Wind wind = intent.getParcelableExtra(App.NAME_WIND_CLASS);

        ImageView image;
        TextView txtCityName,
                 txtHumidity,
                 txtSunrise,
                 txtSunset,
                 txtPressure,
                 txtWind,
                 txtTemperature,
                 txtMinTemperature,
                 txtMaxTemperature,
                 txtDescription;

        image = findViewById(R.id.imgDetailCityWeather);

        txtCityName = findViewById(R.id.txtCityName);
        txtHumidity = findViewById(R.id.txtHumidity);
        txtSunrise = findViewById(R.id.txtSunrise);
        txtSunset = findViewById(R.id.txtSunset);
        txtPressure = findViewById(R.id.txtPressure);
        txtWind = findViewById(R.id.txtWind);
        txtTemperature = findViewById(R.id.txtTemperature);
        txtMinTemperature = findViewById(R.id.txtMinTemperature);
        txtMaxTemperature = findViewById(R.id.txtMaxTemperature);
        txtDescription = findViewById(R.id.txtDescription);


        String url =
                "https://openweathermap.org/img/w/" +
                        weather.icon +
                        ".png";
        Picasso.get().load(url).into(image);

        String temperature = String.valueOf(main.temp) + " °C";
        String minTemperature = String.valueOf(main.temp_min) + " °C";
        String maxTemperature = String.valueOf(main.temp_max) + " °C";
        String fieldWind = "Speed: " + String.valueOf(wind.speed) + " m/s  Deg: " + String.valueOf(wind.deg);
        String humidity = String.valueOf(main.humidity) + "%";
        String description = weather.description;
        String pressure = String.valueOf(main.pressure) + " hpa";
        String sunrise = App.convertUnixToHour(sys.sunrise);
        String sunset = App.convertUnixToHour(sys.sunset);


        txtDescription.setText(description);
        txtCityName.setText(name);
        txtTemperature.setText(temperature);
        txtMinTemperature.setText(minTemperature);
        txtMaxTemperature.setText(maxTemperature);
        txtWind.setText(fieldWind);
        txtHumidity.setText(humidity);
        txtPressure.setText(pressure);
        txtSunrise.setText(sunrise);
        txtSunset.setText(sunset);
        
    }

}
