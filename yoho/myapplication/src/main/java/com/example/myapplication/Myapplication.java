package com.example.myapplication;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseApplication;
import com.mob.MobSDK;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class Myapplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
        ZXingLibrary.initDisplayOpinion(this);
        MobSDK.init(this);
    }
}
