package com.lenecoproekt.simpleweather;

import java.io.Serializable;

public class AppSettings implements Serializable {
    private boolean nightMode = false;
    private boolean celsius = true;
    private boolean wind = true;
    private boolean dayLong = true;
    private boolean precipitation = true;

    public AppSettings(boolean nightMode, boolean celsius, boolean wind, boolean dayLong, boolean precipitation) {
        this.nightMode = nightMode;
        this.celsius = celsius;
        this.wind = wind;
        this.dayLong = dayLong;
        this.precipitation = precipitation;
    }

    public boolean isNightMode() {
        return nightMode;
    }

    public boolean isCelsius() {
        return celsius;
    }

    public boolean isWind() {
        return wind;
    }

    public boolean isDayLong() {
        return dayLong;
    }

    public boolean isPrecipitation() {
        return precipitation;
    }
}
