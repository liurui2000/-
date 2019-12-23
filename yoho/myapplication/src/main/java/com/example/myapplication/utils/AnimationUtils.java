package com.example.myapplication.utils;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * @author ：created by
 * Create Date ：2019/12/17 13
 * Package_Name : yoho
 */
public class AnimationUtils {

    /**
     * 渐隐渐现动画
     */
    public void showAndHiddenAnimation(final View view,float startR,float endR){
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", startR, endR);
        alpha.setDuration(0);
        alpha.start();
        alpha = null;
    }
}
