package com.save.bike.watchflow;

public class MenuItem {
    public String title;
    public String status;
    public String date;
    public Class launchActivity;

    public MenuItem(String title, String status, String date, Class launchActivity) {
        this.title = title;
        this.status = status;
        this.date = date;
        this.launchActivity = launchActivity;
    }
}
