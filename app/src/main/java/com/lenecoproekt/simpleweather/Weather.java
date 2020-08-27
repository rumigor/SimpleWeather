package com.lenecoproekt.simpleweather;

import java.io.Serializable;

public class Weather implements Serializable {
    private String temperature;
    private String wind;
    private String sunrise;
    private String sunset;
    private String precipitation;
    private int wCond;

    public Weather(String temperature, String wind, String sunrise, String sunset, String precipitation, int wCond) {
        this.temperature = temperature;
        this.wind = wind;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.precipitation = precipitation;
        this.wCond = wCond;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWind() {
        return wind;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public int getwCond() {
        return wCond;
    }
}
