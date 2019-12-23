package com.example.myapplication.mvp.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.entity.AddCarEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {


    @BindView(R.id.web_back)
    public ImageView web_back;
    @BindView(R.id.web_addcar)
    Button webAddcar;

    @BindView(R.id.web_reb)
    CheckBox web_reb;
    private String uri;
    private String ispa = "0";

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().lreModule(new LreModule(this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_web_view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        uri = getIntent().getStringExtra("uri");
        ispa = getIntent().getStringExtra("ispa");

        SharedPreferences islogin = getSharedPreferences("islogin", MODE_PRIVATE);
        boolean islike = islogin.getBoolean("islike", false);
        if (islike){
            web_reb.setChecked(true);
        }else {
            web_reb.setChecked(false);
        }

        web_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
                intent.putExtra("ispage", ispa);
                startActivity(intent);
                finish();
            }
        });
        web_reb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){
                    web_reb.setChecked(true);
                    SharedPreferences islogin = getSharedPreferences("islogin", MODE_PRIVATE);
                    SharedPreferences.Editor edit = islogin.edit();
                    edit.putBoolean("islike",true);
                    edit.commit();
                    Toast.makeText(WebViewActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                }else {
                    web_reb.setChecked(false);
                    SharedPreferences islogin = getSharedPreferences("islogin", MODE_PRIVATE);
                    SharedPreferences.Editor edit = islogin.edit();
                    edit.putBoolean("islike",false);
                    edit.commit();
                    Toast.makeText(WebViewActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                }

            }
        });


        webAddcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object=new JSONObject();
                try {
                    object.put("userid","1");
                    object.put("goodsid","1");
                    object.put("shopname","marsLR");
                    object.put("shopcolor","1");
                    object.put("shopsize","1");
                    object.put("shopnum","1");
                    object.put("shopprice","1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mPresenter.lreAll(object.toString(), ApiDoman.ADD_CAR);

            }
        });

    }


    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.ADD_CAR){
            if (entity instanceof AddCarEntity){
                AddCarEntity addCarEntity = (AddCarEntity) entity;
                String msg = addCarEntity.getMsg();
                if (msg.equals("添加成功")){
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void refreshSuccess(BaseEntity entity) {

    }

    @Override
    public void loadSuceess(BaseEntity entity) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
