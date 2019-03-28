package com.baulin.alexander.weatherapp.dagger2.modules;

import com.baulin.alexander.weatherapp.dagger2.scopes.MainActivityScope;
import com.baulin.alexander.weatherapp.mvp.presenter.Presenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    @MainActivityScope
    com.baulin.alexander.weatherapp.mvp.interfaces.Presenter getPresenter() {
        return new Presenter();
    }
}