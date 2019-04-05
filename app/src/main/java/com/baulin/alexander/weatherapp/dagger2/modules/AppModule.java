package com.baulin.alexander.weatherapp.dagger2.modules;

import android.content.Context;

import com.baulin.alexander.weatherapp.mvp.interfaces.Model;
import com.baulin.alexander.weatherapp.mvp.model.Data;
import com.baulin.alexander.weatherapp.mvp.model.DeviceLocation;
import com.baulin.alexander.weatherapp.mvp.model.retrofit.RetrofitAPI;
import com.baulin.alexander.weatherapp.mvp.model.retrofit.RetrofitClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context context;

    public AppModule(Context ctx) {
        context = ctx;
    }

    @Provides
    @Singleton
    Context context() {
        return context;
    }

    @Provides
    @Singleton
    Model getData() {
        return new Data();
    }

    @Provides
    @Singleton
    DeviceLocation getDeviceLocation() {
        return new DeviceLocation();
    }

    @Provides
    @Singleton
    RetrofitAPI getRetrofitClient() {
        return RetrofitClient.getInstance().create(RetrofitAPI .class);
    }

}