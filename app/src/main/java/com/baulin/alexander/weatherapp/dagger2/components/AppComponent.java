package com.baulin.alexander.weatherapp.dagger2.components;

import android.content.Context;

import com.baulin.alexander.weatherapp.dagger2.modules.AppModule;
import com.baulin.alexander.weatherapp.mvp.interfaces.Model;
import com.baulin.alexander.weatherapp.mvp.presenter.Presenter;
import com.baulin.alexander.weatherapp.mvp.view.adapter.WeatherAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class})
public interface AppComponent {
    Context getContext();
    Model getData();
    void injectPresenter(Presenter presenter);
    void injectPostAdapter(WeatherAdapter adapter);

}
