package com.example.myapplication.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：created by
 * Create Date ：2019/12/13 14
 * Package_Name : yoho
 */
public class RegularUtils {
    private static RegularUtils instance;
    private RegularUtils(){}

    public static RegularUtils getInstance() {
        if(instance == null){
            synchronized (RegularUtils.class){
                if(instance == null){
                    instance = new RegularUtils();
                }
            }
        }
        return instance;
    }


    public boolean IsTelNumber(String str){
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public boolean IsEmail(String str){
        Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher mc = pattern.matcher(str);
        return mc.matches();
    }
}
