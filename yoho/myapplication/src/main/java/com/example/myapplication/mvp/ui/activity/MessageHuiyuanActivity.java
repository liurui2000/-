package com.example.myapplication.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MessageHuiyuanActivity extends BaseActivity {

    @BindView(R.id.huiyuan_title)
    TextView huiyuanTitle;
    @BindView(R.id.huiyuan_back)
    TextView huiyuanBack;
    @BindView(R.id.huiyuan_guang)
    Button huiyuanGuang;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_huiyuan);
        bind = ButterKnife.bind(this);
        String title = getIntent().getStringExtra("title");
        if (title!=null){
            huiyuanTitle.setText(title);
        }
        huiyuanBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        huiyuanGuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MessageHuiyuanActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_message_huiyuan;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
