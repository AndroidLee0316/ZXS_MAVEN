package com.pingan.debug.net.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.pasc.lib.net.ApiGenerator;
import com.pasc.lib.net.param.BaseParam;
import com.pasc.lib.net.resp.BaseResp;
import com.pingan.debug.net.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class WeatherActivity extends AppCompatActivity {
  @BindView(R.id.weather_view) TextView weatherView;
  @BindView(R.id.method_view) TextView methodView;
  @BindView(R.id.url_view) TextView urlView;
  @BindView(R.id.params_view) TextView paramsView;
  @BindView(R.id.diff_base_url_view) TextView diffBaseUrlView;
  @BindView(R.id.diff_url_weather_view) TextView diffWeatherView;
  @BindView(R.id.xml_weather_view) TextView xmlWeatherView;
  @BindView(R.id.xml_weather_result_view) TextView xmlWeatherResultView;
  private CompositeDisposable disposables = new CompositeDisposable();


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weather);
    ButterKnife.bind(this);
    urlView.setText("请求链接："+"https://smt-app-stg.pingan.com.cn:58443/"+"smtapp/weather/queryWeather.do");
    methodView.setText("请求方法："+"POST");
    WeatherDetailParam weatherDetailParam = new WeatherDetailParam(114.06913, 22.570676, "深圳");
    paramsView.setText("请求参数："+weatherDetailParam.toString());
    disposables.add(ApiGenerator.createApi(WeatherService.class)
            .getSimpleWeather(new BaseParam<>(weatherDetailParam))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<BaseResp<SimpleWeatherResp>>() {
              @Override
              public void onSuccess(BaseResp<SimpleWeatherResp> simpleWeatherRespBaseResp) {
                if (simpleWeatherRespBaseResp.data!=null){
                  weatherView.setText("返回结果 温度："+simpleWeatherRespBaseResp.data.temp);
                }
              }

              @Override public void onError(Throwable e) {
                Toast.makeText(WeatherActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
              }
            }));

    diffBaseUrlView.setText("切换 baseUrl 为 ttps://smt-app.pingan.com.cn/");
    disposables.add(ApiGenerator.createApi("https://smt-app.pingan.com.cn/", WeatherService.class)
            .getSimpleWeather(new BaseParam<>(weatherDetailParam))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<BaseResp<SimpleWeatherResp>>() {
              @Override
              public void onSuccess(BaseResp<SimpleWeatherResp> simpleWeatherRespBaseResp) {
                if (simpleWeatherRespBaseResp.data!=null){
                  diffWeatherView.setText("返回结果 温度："+simpleWeatherRespBaseResp.data.temp);
                }
              }

              @Override public void onError(Throwable e) {
                Toast.makeText(WeatherActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
              }
            }));
    xmlWeatherView.setText("请求链接：http://wthrcdn.etouch.cn/WeatherApi?city=北京 \n 请求方式:Get \n返回数据结构：XML");

    disposables.add(ApiGenerator.createApi(WeatherService.class)
            .getWeatherByCity("北京")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<WeatherResp>() {
              @Override public void onSuccess(WeatherResp weatherResp) {
                xmlWeatherResultView.setText(weatherResp.getCity()+"温度："+weatherResp.getWendu());
              }

              @Override public void onError(Throwable e) {
                Toast.makeText(WeatherActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
              }
            }));

  }

  @Override protected void onDestroy() {
    super.onDestroy();
    disposables.clear();
  }
}
