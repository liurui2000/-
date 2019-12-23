package com.example.myapplication.mvp.model.entity;

public class Address {

    /**
     * user_id : 1
     * user_name : wzh
     * phone : 12345678
     * address_area : 北京市
     * address_detail : 八维
     * address_tag : 家
     */

    private String user_id;
    private String user_name;
    private String phone;
    private String address_area;
    private String address_detail;
    private String address_tag;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress_area() {
        return address_area;
    }

    public void setAddress_area(String address_area) {
        this.address_area = address_area;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getAddress_tag() {
        return address_tag;
    }

    public void setAddress_tag(String address_tag) {
        this.address_tag = address_tag;
    }

    public Address(String user_id, String user_name, String phone, String address_area, String address_detail, String address_tag) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.phone = phone;
        this.address_area = address_area;
        this.address_detail = address_detail;
        this.address_tag = address_tag;
    }
}
