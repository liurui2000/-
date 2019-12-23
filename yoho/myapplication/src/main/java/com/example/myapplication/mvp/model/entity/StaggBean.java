package com.example.myapplication.mvp.model.entity;

public class StaggBean {
    int pic;
    String title;

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public StaggBean(int pic, String title) {
        this.pic = pic;
        this.title = title;
    }
}
