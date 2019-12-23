package com.example.myapplication.mvp.model.entity;

public class SqlBean {
    int money;
    String title;
    String pic;
    int count;
    Boolean check;
    String color;
    String id;
    String userid;
    String goodsid;
    String size;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Boolean getCheck() {
        return check;
    }

    @Override
    public String toString() {
        return "SqlBean{" +
                "money=" + money +
                ", title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                ", count=" + count +
                ", check=" + check +
                ", color='" + color + '\'' +
                ", id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", goodsid='" + goodsid + '\'' +
                ", size='" + size + '\'' +
                ", select='" + select + '\'' +
                '}';
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public SqlBean(int money, String title, String pic, int count, Boolean check, String color, String id, String userid, String goodsid, String size, String select) {
        this.money = money;
        this.title = title;
        this.pic = pic;
        this.count = count;
        this.check = check;
        this.color = color;
        this.id = id;
        this.userid = userid;
        this.goodsid = goodsid;
        this.size = size;
        this.select = select;
    }

    String select;

    public SqlBean() {
    }
}
