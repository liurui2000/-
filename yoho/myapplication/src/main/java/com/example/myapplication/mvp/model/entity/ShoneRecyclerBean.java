package com.example.myapplication.mvp.model.entity;

public class ShoneRecyclerBean {
    String pic;
    String title;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ShoneRecyclerBean(String pic, String title) {
        this.pic = pic;
        this.title = title;
    }
}
