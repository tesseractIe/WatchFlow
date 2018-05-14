package com.save.bike.watchflow;

import android.support.annotation.NonNull;

public class HeartRate {
    private int heartRate;
    private int minutes;

    public HeartRate(int heartRate, int minutesOfDay) {
        this.heartRate = heartRate;
        this.minutes = minutesOfDay;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return "heartRate: "+heartRate+"  | minutes: "+minutes;
    }
}
