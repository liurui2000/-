package com.example.myapplication.mvp.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class SerOfMyselfActivity extends AppCompatActivity {

    @BindView(R.id.serof_header)
    ImageView serofHeader;
    @BindView(R.id.serof_left_st)
    TextView serofLeftSt;
    @BindView(R.id.serof_left_rd)
    TextView serofLeftRd;
    @BindView(R.id.serof_right_st)
    TextView serofRightSt;
    @BindView(R.id.serof_right_rd)
    TextView serofRightRd;
    @BindView(R.id.serof_left)
    LinearLayout serofLeft;
    @BindView(R.id.serof_right)
    LinearLayout serofRight;
    @BindView(R.id.serof_share)
    ImageView serofShare;
    @BindView(R.id.ser_back)
    ImageView ser_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ser_of_myself);
        ButterKnife.bind(this);

        Glide.with(this).load(R.drawable.k).apply(RequestOptions.circleCropTransform()).into(serofHeader);
        ser_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        serofLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serofLeftSt.setTextColor(Color.parseColor("#000000"));
                serofLeftRd.setTextColor(Color.parseColor("#000000"));
                serofRightSt.setTextColor(Color.parseColor("#bdbdbd"));
                serofRightRd.setTextColor(Color.parseColor("#bdbdbd"));
            }
        });


        serofRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serofRightSt.setTextColor(Color.parseColor("#000000"));
                serofRightRd.setTextColor(Color.parseColor("#000000"));
                serofLeftSt.setTextColor(Color.parseColor("#bdbdbd"));
                serofLeftRd.setTextColor(Color.parseColor("#bdbdbd"));
            }
        });

        serofShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });
    }

    //java
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("分享至");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，确保SDcard下面存在此张图片
        oks.setImagePath("/sdcard/test.jpg");
        // url在微信、Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
