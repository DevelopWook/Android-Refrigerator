package com.example.mira.refrigerator;

/**
 * Created by MiRa on 2015-11-27.
 */
public class Product {
    private String location;
    private String fraction;
    private String name;
    private String buyDay;
    private String lastDay;
    private String image;

    public Product() {};
    public Product(String location, String fraction, String name,
                   String buyDay, String lastDay, String image){
        this.location = location;
        this.fraction = fraction;
        this.name = name;
        this.buyDay = buyDay;
        this.lastDay = lastDay;
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
