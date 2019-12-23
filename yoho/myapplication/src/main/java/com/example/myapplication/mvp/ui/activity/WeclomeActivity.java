package com.example.myapplication.mvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.myapplication.R;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import java.util.Timer;
import java.util.TimerTask;

public class WeclomeActivity extends BaseActivity {
    @SuppressLint("HandlerLeak")
    Timer timer;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_weclome;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ImmersionBar.with(this).init();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                startActivity(new Intent(WeclomeActivity.this,WeclomeloadActivity.class));
                ARouter.getInstance().build("/weclomeload/nimasile").navigation();
                timer.cancel();
                finish();
            }
        },3000);
    }
}
