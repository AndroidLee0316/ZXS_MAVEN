package com.pingan.debug.net.weather;

import com.pasc.lib.net.annotation.Xml;
import com.pasc.lib.net.param.BaseParam;
import com.pasc.lib.net.resp.BaseResp;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WeatherService {
  @FormUrlEncoded @POST("smtapp/weather/queryWeather.do")
  Single<BaseResp<SimpleWeatherResp>> getSimpleWeather(
          @Field("jsonData") BaseParam<WeatherDetailParam> param);

  @Xml @GET("http://wthrcdn.etouch.cn/WeatherApi") Single<WeatherResp> getWeatherByCity(
          @Query("city") String city);
}
