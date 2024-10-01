package com.pingan.debug.net.weather;

import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

@org.simpleframework.xml.Root(name = "resp") public class WeatherResp {

  @Element(name="city", required = false) public String city;
  @Element(name="shidu", required = false) private String shidu;
  @Element(name="fengli", required = false) private String fengli;
  @Element(name="fengxiang", required = false) private String fengxiang;
  @Element(name="wendu", required = false) private String wendu;
  @Element(name="sunset_2", required = false) private String sunset_2;
  @Element(name="yesterday", required = false) private YesterdayBean yesterday;
  @Element(name="sunset_1", required = false) private String sunset_1;
  @Element(name="sunrise_2", required = false) private String sunrise_2;
  @Element(name="environment", required = false) private EnvironmentBean environment;
  @Element(name="updatetime", required = false) private String updatetime;
  @Element(name="sunrise_1", required = false) private String sunrise_1;
  @ElementList(name="zhishus", required = false) private List<ZhishuBean> zhishus;
  @ElementList(name="forecast", required = false) private List<WeatherBean> forecast;

  public String getShidu() {
    return shidu;
  }

  public void setShidu(String shidu) {
    this.shidu = shidu;
  }

  public String getFengli() {
    return fengli;
  }

  public void setFengli(String fengli) {
    this.fengli = fengli;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getFengxiang() {
    return fengxiang;
  }

  public void setFengxiang(String fengxiang) {
    this.fengxiang = fengxiang;
  }

  public String getWendu() {
    return wendu;
  }

  public void setWendu(String wendu) {
    this.wendu = wendu;
  }

  public String getSunset_2() {
    return sunset_2;
  }

  public void setSunset_2(String sunset_2) {
    this.sunset_2 = sunset_2;
  }

  public YesterdayBean getYesterday() {
    return yesterday;
  }

  public void setYesterday(YesterdayBean yesterday) {
    this.yesterday = yesterday;
  }

  public String getSunset_1() {
    return sunset_1;
  }

  public void setSunset_1(String sunset_1) {
    this.sunset_1 = sunset_1;
  }

  public String getSunrise_2() {
    return sunrise_2;
  }

  public void setSunrise_2(String sunrise_2) {
    this.sunrise_2 = sunrise_2;
  }

  public EnvironmentBean getEnvironment() {
    return environment;
  }

  public void setEnvironment(EnvironmentBean environment) {
    this.environment = environment;
  }

  public String getUpdatetime() {
    return updatetime;
  }

  public void setUpdatetime(String updatetime) {
    this.updatetime = updatetime;
  }

  public String getSunrise_1() {
    return sunrise_1;
  }

  public void setSunrise_1(String sunrise_1) {
    this.sunrise_1 = sunrise_1;
  }

  public List<ZhishuBean> getZhishus() {
    return zhishus;
  }

  public void setZhishus(List<ZhishuBean> zhishus) {
    this.zhishus = zhishus;
  }

  public List<WeatherBean> getForecast() {
    return forecast;
  }

  public void setForecast(List<WeatherBean> forecast) {
    this.forecast = forecast;
  }

  @org.simpleframework.xml.Root(name = "yesterday") public static class YesterdayBean {
    @Element(name="date_1", required = false) private String date_1;
    @Element(name="high_1", required = false) private String high_1;
    @Element(name="low_1", required = false) private String low_1;
    @Element(name="day_1", required = false) private Day1Bean day_1;
    @Element(name="night_1", required = false) private Night1Bean night_1;

    public String getDate_1() {
      return date_1;
    }

    public void setDate_1(String date_1) {
      this.date_1 = date_1;
    }

    public String getHigh_1() {
      return high_1;
    }

    public void setHigh_1(String high_1) {
      this.high_1 = high_1;
    }

    public String getLow_1() {
      return low_1;
    }

    public void setLow_1(String low_1) {
      this.low_1 = low_1;
    }

    public Day1Bean getDay_1() {
      return day_1;
    }

    public void setDay_1(Day1Bean day_1) {
      this.day_1 = day_1;
    }

    public Night1Bean getNight_1() {
      return night_1;
    }

    public void setNight_1(Night1Bean night_1) {
      this.night_1 = night_1;
    }

    @org.simpleframework.xml.Root(name = "day_1") public static class Day1Bean {
      @Element(name="type_1", required = false) private String type_1;
      @Element(name="fx_1", required = false) private String fx_1;
      @Element(name="fl_1", required = false) private String fl_1;

      public String getType_1() {
        return type_1;
      }

      public void setType_1(String type_1) {
        this.type_1 = type_1;
      }

      public String getFx_1() {
        return fx_1;
      }

      public void setFx_1(String fx_1) {
        this.fx_1 = fx_1;
      }

      public String getFl_1() {
        return fl_1;
      }

      public void setFl_1(String fl_1) {
        this.fl_1 = fl_1;
      }
    }

    @org.simpleframework.xml.Root(name = "night_1") public static class Night1Bean {
      @Element(name="type_1", required = false) private String type_1;
      @Element(name="fx_1", required = false) private String fx_1;
      @Element(name="fl_1", required = false) private String fl_1;

      public String getType_1() {
        return type_1;
      }

      public void setType_1(String type_1) {
        this.type_1 = type_1;
      }

      public String getFx_1() {
        return fx_1;
      }

      public void setFx_1(String fx_1) {
        this.fx_1 = fx_1;
      }

      public String getFl_1() {
        return fl_1;
      }

      public void setFl_1(String fl_1) {
        this.fl_1 = fl_1;
      }
    }
  }

  @org.simpleframework.xml.Root(name = "environment") public static class EnvironmentBean {
    @Element(name="aqi", required = false) private String aqi;
    @Element(name="pm25", required = false) private String pm25;
    @Element(name="suggest", required = false) private String suggest;
    @Element(name="quality", required = false) private String quality;
    @Element(name="MajorPollutants", required = false) private String MajorPollutants;
    @Element(name="o3", required = false) private String o3;
    @Element(name="co", required = false) private String co;
    @Element(name="pm10", required = false) private String pm10;
    @Element(name="so2", required = false) private String so2;
    @Element(name="no2", required = false) private String no2;
    @Element(name="time", required = false) private String time;

    public String getAqi() {
      return aqi;
    }

    public void setAqi(String aqi) {
      this.aqi = aqi;
    }

    public String getPm25() {
      return pm25;
    }

    public void setPm25(String pm25) {
      this.pm25 = pm25;
    }

    public String getSuggest() {
      return suggest;
    }

    public void setSuggest(String suggest) {
      this.suggest = suggest;
    }

    public String getQuality() {
      return quality;
    }

    public void setQuality(String quality) {
      this.quality = quality;
    }

    public String getMajorPollutants() {
      return MajorPollutants;
    }

    public void setMajorPollutants(String MajorPollutants) {
      this.MajorPollutants = MajorPollutants;
    }

    public String getO3() {
      return o3;
    }

    public void setO3(String o3) {
      this.o3 = o3;
    }

    public String getCo() {
      return co;
    }

    public void setCo(String co) {
      this.co = co;
    }

    public String getPm10() {
      return pm10;
    }

    public void setPm10(String pm10) {
      this.pm10 = pm10;
    }

    public String getSo2() {
      return so2;
    }

    public void setSo2(String so2) {
      this.so2 = so2;
    }

    public String getNo2() {
      return no2;
    }

    public void setNo2(String no2) {
      this.no2 = no2;
    }

    public String getTime() {
      return time;
    }

    public void setTime(String time) {
      this.time = time;
    }
  }

  @org.simpleframework.xml.Root(name = "zhishu") public static class ZhishuBean {
    @Element(name="name", required = false) private String name;
    @Element(name="value", required = false) private String value;
    @Element(name="detail", required = false) private String detail;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }

    public String getDetail() {
      return detail;
    }

    public void setDetail(String detail) {
      this.detail = detail;
    }
  }

  @org.simpleframework.xml.Root(name = "weather") public static class WeatherBean {
    @Element(name="date", required = false) private String date;
    @Element(name="high", required = false) private String high;
    @Element(name="low", required = false) private String low;
    @Element(name="day", required = false) private DayBean day;
    @Element(name="night", required = false) private NightBean night;

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getHigh() {
      return high;
    }

    public void setHigh(String high) {
      this.high = high;
    }

    public String getLow() {
      return low;
    }

    public void setLow(String low) {
      this.low = low;
    }

    public DayBean getDay() {
      return day;
    }

    public void setDay(DayBean day) {
      this.day = day;
    }

    public NightBean getNight() {
      return night;
    }

    public void setNight(NightBean night) {
      this.night = night;
    }

    @org.simpleframework.xml.Root(name = "day") public static class DayBean {
      @Element(name="type", required = false) private String type;
      @Element(name="fengxiang", required = false) private String fengxiang;
      @Element(name="fengli", required = false) private String fengli;

      public String getType() {
        return type;
      }

      public void setType(String type) {
        this.type = type;
      }

      public String getFengxiang() {
        return fengxiang;
      }

      public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
      }

      public String getFengli() {
        return fengli;
      }

      public void setFengli(String fengli) {
        this.fengli = fengli;
      }
    }

    @org.simpleframework.xml.Root(name = "night") public static class NightBean {
      @Element(name="type", required = false) private String type;
      @Element(name="fengxiang", required = false) private String fengxiang;
      @Element(name="fengli", required = false) private String fengli;

      public String getType() {
        return type;
      }

      public void setType(String type) {
        this.type = type;
      }

      public String getFengxiang() {
        return fengxiang;
      }

      public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
      }

      public String getFengli() {
        return fengli;
      }

      public void setFengli(String fengli) {
        this.fengli = fengli;
      }
    }
  }
}
