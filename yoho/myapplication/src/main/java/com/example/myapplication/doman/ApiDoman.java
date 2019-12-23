package com.example.myapplication.doman;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiDoman {

    public static final int CATEGORY_ALL=1;
    public static final int CATEGORY_GOODS=2;
    public static final int BRAND_LIST=3;
    public static final int SHOES_LIST=4;
    public static final int BANNER=5;
    public static final int SEE_LIST=6;
    public static final int GOODS_LIST=7;
    public static final int COMMUNITY_LIST=8;
    public static final int LOGIN_RESULT = 9;
    public static final int REGISTER_RESULT = 10;
    public static final int ADD_CAR = 11;
    public static final int CAR_LIST = 12;
    public static final int QUERY_USER = 13;
    public static final int UPLOAD_HEAD = 14;
    public static final int CHANGE_USER = 15;
    public static final int ORDER_LIST = 16;

    public static final int ADD_ADDRESS = 17;
    public static final int ADDRESSMANAGER = 18;
    public static final int DELETE_ADDRESS = 19;
    public static final int FOOT_PRINT = 20;
    public static final int COUPON_LIST = 21;
    public static String joint(String name,String id){
        String str = "";
        try {
            JSONObject object=new JSONObject();
            object.put(name,id);
            str=object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }
}
