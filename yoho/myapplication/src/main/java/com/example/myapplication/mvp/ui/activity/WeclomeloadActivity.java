package com.example.myapplication.mvp.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.myapplication.R;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import java.util.Timer;
import java.util.TimerTask;

@Route(path = "/weclomeload/nimasile")
public class WeclomeloadActivity extends BaseActivity implements View.OnClickListener {

    private ImageView load_iv;
    private Button bnt_go;
    private AnimatorSet animatorSet;
    private AnimatorSet animatorSet1;
    private Timer timer;
    private int a = 7000;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==101){
                a = a-1000;
                if (a<=2000){
                    timer.cancel();
                    bnt_go.setVisibility(View.GONE);
                }
            }
        }
    };

    private void initView() {
        load_iv = (ImageView) findViewById(R.id.load_iv);
        bnt_go = (Button) findViewById(R.id.bnt_go);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(load_iv, "alpha", 0.3f, 1f);
        animatorSet1 = new AnimatorSet();
        animatorSet1.setDuration(4000);
        animatorSet1.play(alpha);
        animatorSet1.start();
        animatorSet1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(load_iv, "scaleX", 1.05f, 1f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(load_iv, "scaleY", 1.05f, 1f);
                animatorSet = new AnimatorSet();
                animatorSet.setDuration(6000);
                animatorSet.play(scaleX).with(scaleY);
                animatorSet.start();
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
//                        startActivity(new Intent(WeclomeloadActivity.this,MainActivity.class));
                        if (animatorSet!=null){
                            timer.cancel();
                            Log.e("lr","动画结束跳转");
                            ARouter.getInstance().build("/mains/nimaxiang").withTransition(R.anim.in,R.anim.go).navigation(WeclomeloadActivity.this);
                            finish();
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(101);

            }
        },0,1000);

        bnt_go.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bnt_go:

                Log.e("lr","点击跳转");
                ARouter.getInstance().build("/mains/nimaxiang").withTransition(R.anim.in,R.anim.go).navigation(this);
                if (animatorSet!=null){
                    animatorSet.cancel();
                }

                animatorSet1.cancel();
                timer.cancel();

//                animatorSet1=null;
//                animatorSet=null;
                finish();
                break;
        }
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_weclomeload;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ImmersionBar.with(this).init();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        animatorSet = null;
        animatorSet1 = null;
    }
}
