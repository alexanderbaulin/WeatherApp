package com.baulin.alexander.weatherapp.mvp.model.fromJSON.city;

import android.os.Parcel;
import android.os.Parcelable;

public class Sys implements Parcelable {

    public int type;
    public int id;
    public double message;
    public String country;
    public int sunrise;
    public int sunset;

    protected Sys(Parcel in) {
        type = in.readInt();
        id = in.readInt();
        message = in.readDouble();
        country = in.readString();
        sunrise = in.readInt();
        sunset = in.readInt();
    }

    public static final Creator<Sys> CREATOR = new Creator<Sys>() {
        @Override
        public Sys createFromParcel(Parcel in) {
            return new Sys(in);
        }

        @Override
        public Sys[] newArray(int size) {
            return new Sys[size];
        }
    };

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeInt(id);
        dest.writeDouble(message);
        dest.writeString(country);
        dest.writeInt(sunrise);
        dest.writeInt(sunset);
    }
}
