package com.example.myapplication.mvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.CarListEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.ui.fragment.ClassifyFragment;
import com.example.myapplication.mvp.ui.fragment.CommunityFragment;
import com.example.myapplication.mvp.ui.fragment.HomeFragment;
import com.example.myapplication.mvp.ui.fragment.MineFragment;
import com.example.myapplication.mvp.ui.fragment.UfoFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@Route(path = "/mains/nimaxiang")
public class MainActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {

    @BindView(R.id.main_framelayout)
    FrameLayout main_framelayout;

    @BindView(R.id.main_gb)
    RadioGroup main_gb;

    @BindView(R.id.main_homepage_rb)
    RadioButton main_homepage_rb;

    @BindView(R.id.main_classify_rb)
    RadioButton main_classify_rb;

    @BindView(R.id.main_shoes_ufo_rb)
    RadioButton main_shoes_ufo_rb;

    @BindView(R.id.main_community_rb)
    RadioButton main_community_rb;

    @BindView(R.id.main_mine_rb)
    RadioButton main_mine_rb;

    @BindView(R.id.main_ufo_img)
    ImageView main_ufo_img;

    @BindView(R.id.relayout_car)
    RelativeLayout relayout_car;

    @BindView(R.id.main_gwc)
    ImageView main_gwc;

    @BindView(R.id.main_gwc_num)
    TextView main_gwc_num;
    private Unbinder bind;

    MyReceiver myReceiver;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().lreModule(new LreModule(this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ButterKnife.bind(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("mars.liu");
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver,intentFilter);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        JSONObject object=new JSONObject();
        try {
            object.put("userid","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.CAR_LIST);


        int ispage = getIntent().getIntExtra("ispage", 0);
        if (ispage==0){
            main_homepage_rb.setChecked(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new HomeFragment()).commit();
        }else if (ispage==1){
            main_classify_rb.setChecked(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new ClassifyFragment()).commit();

        }else if (ispage==2){
            main_shoes_ufo_rb.setChecked(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new UfoFragment()).commit();

        }else if (ispage==3){
            main_community_rb.setChecked(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new CommunityFragment()).commit();

        }else if (ispage==4){
            main_mine_rb.setChecked(true);
            SharedPreferences islogin = getSharedPreferences("islogin", MODE_PRIVATE);
            boolean islog = islogin.getBoolean("islog", false);
            if (islog == true){
                getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new MineFragment()).commit();
            }else {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        }

        init();

    }

    private void init() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new HomeFragment()).commit();
        main_gb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){
                    case R.id.main_homepage_rb:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new HomeFragment()).commit();
                        break;
                    case R.id.main_classify_rb:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new ClassifyFragment()).commit();
                        break;
                    case R.id.main_shoes_ufo_rb:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new UfoFragment()).commit();
                        break;
                    case R.id.main_community_rb:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new CommunityFragment()).commit();
                        break;
                    case R.id.main_mine_rb:

                        SharedPreferences islogin = getSharedPreferences("islogin", MODE_PRIVATE);
                        boolean islog = islogin.getBoolean("islog", false);
                        if (islog == true){
                            getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new MineFragment()).commit();
                        }else {
                            startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        }

                        break;
                }
            }
        });

        relayout_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShoppingCarActivity.class));
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        unregisterReceiver(myReceiver);

    }

    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.CAR_LIST){
            if (entity instanceof CarListEntity){
                CarListEntity carListEntity = (CarListEntity) entity;
                List<CarListEntity.ValuesBean> values = carListEntity.getValues();
                int size = values.size();
                main_gwc_num.setText(size+"");
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
    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("mars.liu")){
                JSONObject object=new JSONObject();
                try {
                    object.put("userid","1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mPresenter.lreAll(object.toString(), ApiDoman.CAR_LIST);

                int num = intent.getIntExtra("num", 0);
                    if (num==1){
                        relayout_car.setVisibility(View.VISIBLE);
                    }else if (num==2){
                        relayout_car.setVisibility(View.GONE);
                    }
            }

        }
    }
}
