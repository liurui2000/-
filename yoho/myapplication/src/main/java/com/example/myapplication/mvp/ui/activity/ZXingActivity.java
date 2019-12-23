package com.example.myapplication.mvp.ui.activity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ZXingActivity extends BaseActivity {

    @BindView(R.id.zxing_back)
    ImageView zxingBack;
    @BindView(R.id.zxing_refresh)
    Button zxingRefresh;
    @BindView(R.id.zxing_head)
    ImageView zxingHead;
    @BindView(R.id.zxing_codepic)
    ImageView zxingCodepic;
    private Unbinder bind;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_zxing;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Bitmap image = CodeUtils.createImage("刘瑞可是最爱你最爱你的爸爸呀", 300, 300, BitmapFactory.decodeResource(getResources(), R.drawable.k));
        zxingCodepic.setImageBitmap(image);

        zxingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Glide.with(this).load(R.drawable.k).apply(RequestOptions.circleCropTransform()).into(zxingHead);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         bind = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        finish();
    }
}
