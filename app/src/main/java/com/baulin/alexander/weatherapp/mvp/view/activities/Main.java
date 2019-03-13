package com.baulin.alexander.weatherapp.mvp.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baulin.alexander.weatherapp.R;
import com.baulin.alexander.weatherapp.mvp.presenter.Presenter;


public class Main extends AppCompatActivity {


    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter();
        presenter.test();




    }
}
