package com.pingan.debug.net.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huanglihou519 on 2018/7/3.
 */

public class SimpleWeatherResp {
    @SerializedName("t") public String temp;
    @SerializedName("heFengIconUrl") public String icon;
}
