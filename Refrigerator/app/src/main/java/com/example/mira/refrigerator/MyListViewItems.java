package com.example.mira.refrigerator;

import android.graphics.drawable.Drawable;

/**
 * Created by MiRa on 2015-11-27.
 */
public class MyListViewItems {
    private Drawable image;
    private String name;
    private String buyDay;
    private String lastDay;

    public MyListViewItems(){
        image = null;
    }
    public MyListViewItems(String name, String buyDay, String lastDay){
        this.name = name;
        this.buyDay = buyDay;
        this.lastDay = lastDay;
        this.image = null;
    }

    public Drawable getImageView() {
        return image;
    }

    public void setImageView(Drawable imageView) {
        this.image = imageView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuyDay() {
        return buyDay;
    }

    public void setBuyDay(String buyDay) {
        this.buyDay = buyDay;
    }

    public String getLastDay() {
        return lastDay;
    }

    public void setLastDay(String lastDay) {
        this.lastDay = lastDay;
    }
}
