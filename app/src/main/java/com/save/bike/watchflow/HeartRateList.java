package com.save.bike.watchflow;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class HeartRateList {
    private List<HeartRate> fullHeartRateList;
    private int minFullListHeartRate;
    private int maxFullListHeartRate;

    private List<HeartRate> shortHeartRateList = new ArrayList<>();
    private int fullHeartRateRange;

    private List<HeartRate> detailHeartRateList = new ArrayList<>();
    private int minDetailListHeartRate;
    private int maxDetailListHeartRate;
    private int detailHeartRateRange;

    public HeartRateList(List<HeartRate> list) {
        this.fullHeartRateList = list;
        minFullListHeartRate = Integer.MAX_VALUE;
        maxFullListHeartRate = Integer.MIN_VALUE;
        for (HeartRate hr : fullHeartRateList) {
            if (hr.getHeartRate() > maxFullListHeartRate) {
                maxFullListHeartRate = hr.getHeartRate();
            }
            if (hr.getHeartRate() < minFullListHeartRate) {
                minFullListHeartRate = hr.getHeartRate();
            }
        }
        for (HeartRate event : fullHeartRateList) {
            boolean isFound = false;
            for (HeartRate e : shortHeartRateList) {
                if (e.getMinutes() / 15 == event.getMinutes() / 15){
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                shortHeartRateList.add(event);
            }
        }
        fullHeartRateRange = maxFullListHeartRate - minFullListHeartRate;
    }

    public List<HeartRate> getDetailHeartRateList() {
        return detailHeartRateList;
    }

    public void setDetailHeartRateList(List<HeartRate> detailHeartRateList) {
        minDetailListHeartRate = Integer.MAX_VALUE;
        maxDetailListHeartRate = Integer.MIN_VALUE;
        for (HeartRate hr : detailHeartRateList) {
            if (hr.getHeartRate() > maxDetailListHeartRate) {
                maxDetailListHeartRate = hr.getHeartRate();
            }
            if (hr.getHeartRate() < minDetailListHeartRate) {
                minDetailListHeartRate = hr.getHeartRate();
            }
        }
        detailHeartRateRange = maxDetailListHeartRate - minDetailListHeartRate;
        this.detailHeartRateList = detailHeartRateList;
    }

    public int getFullHeartRateRange() {
        return fullHeartRateRange;
    }

    public int getMinFullListHeartRate() {
        return minFullListHeartRate;
    }

    public int getMaxFullListHeartRate() {
        return maxFullListHeartRate;
    }

    public List<HeartRate> getFullHeartRateList() {
        return fullHeartRateList;
    }

    public List<HeartRate> getShortHeartRateList() {
        return shortHeartRateList;
    }

    public int getMinDetailListHeartRate() {
        return minDetailListHeartRate;
    }

    public int getMaxDetailListHeartRate() {
        return maxDetailListHeartRate;
    }

    public int getDetailHeartRateRange() {
        return detailHeartRateRange;
    }

    @Deprecated
    public void logFullList(String TAG){
        for (HeartRate hr : fullHeartRateList) {
            Log.e(TAG, hr.toString());
        }
    }

    @Deprecated
    public void logShortList(String TAG){
        for (HeartRate hr : shortHeartRateList) {
            Log.e(TAG, hr.toString());
        }
    }

}
