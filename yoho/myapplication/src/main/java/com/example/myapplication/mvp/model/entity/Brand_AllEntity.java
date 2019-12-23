package com.example.myapplication.mvp.model.entity;

public class Brand_AllEntity {
    String title;
    String brand_letter;
    String brand_name;
    String hot_tag;
    String brand_bg;

    @Override
    public String toString() {
        return "Brand_AllEntity{" +
                "title='" + title + '\'' +
                ", brand_letter='" + brand_letter + '\'' +
                ", brand_name='" + brand_name + '\'' +
                ", hot_tag='" + hot_tag + '\'' +
                ", brand_bg='" + brand_bg + '\'' +
                '}';
    }

    public Brand_AllEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand_letter() {
        return brand_letter;
    }

    public void setBrand_letter(String brand_letter) {
        this.brand_letter = brand_letter;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getHot_tag() {
        return hot_tag;
    }

    public void setHot_tag(String hot_tag) {
        this.hot_tag = hot_tag;
    }

    public String getBrand_bg() {
        return brand_bg;
    }

    public void setBrand_bg(String brand_bg) {
        this.brand_bg = brand_bg;
    }

    public Brand_AllEntity(String title, String brand_letter, String brand_name, String hot_tag, String brand_bg) {
        this.title = title;
        this.brand_letter = brand_letter;
        this.brand_name = brand_name;
        this.hot_tag = hot_tag;
        this.brand_bg = brand_bg;
    }
}
