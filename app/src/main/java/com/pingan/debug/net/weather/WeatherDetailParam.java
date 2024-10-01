package com.pingan.debug.net.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huanglihou519 on 2018/4/18.
 */

public class WeatherDetailParam {
    @SerializedName("longitude") public double longitude;
    @SerializedName("dimension") public double latitude;
    @SerializedName("city") public String city;

    public WeatherDetailParam(double longitude, double latitude, String city) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
    }

    @Override public String toString() {
        return "WeatherDetailParam{"
                + "longitude="
                + longitude
                + ", latitude="
                + latitude
                + ", city='"
                + city
                + '\''
                + '}';
    }
}
