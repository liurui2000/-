package com.example.myapplication.mvp.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.myapplication.mvp.model.entity.Address;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.google.gson.Gson;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;

public class AddAddressActivity extends BaseActivity<LrePresenter> implements LreContact.LreView, View.OnClickListener {

    @BindView(R.id.add_addbut)
    Button addBut;
    @BindView(R.id.add_name_ed)
    EditText name;
    @BindView(R.id.add_phone_ed)
    EditText phone;
    @BindView(R.id.add_address_ed)
    EditText address;
    @BindView(R.id.add_xiangxi_ed)
    EditText xiangxi;
    @BindView(R.id.add_back_txt)
    TextView back;
    @BindView(R.id.add_home)
    RadioButton home;
    @BindView(R.id.add_gongsi)
    RadioButton gongsi;
    @BindView(R.id.add_xuexiao)
    RadioButton xuexiao;
    @BindView(R.id.add_qita)
    RadioButton qita;

    String tag = "";
    String id;

    @Override
    public void success(BaseEntity baseEntity, int type) {
        if(type == ApiDoman.ADD_ADDRESS){
            if(baseEntity.getMsg().equals("添加成功")){
                setResult(505);
                finish();
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
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent).lreModule(new LreModule(this)).build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_add_address;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        id = getIntent().getStringExtra("id");
        initOnClick();
    }

    private void initOnClick() {
        back.setOnClickListener(this);
        addBut.setOnClickListener(this);
        home.setOnClickListener(this);
        gongsi.setOnClickListener(this);
        xuexiao.setOnClickListener(this);
        qita.setOnClickListener(this);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_addbut:
                if(name.getText().toString().equals("") || phone.getText().toString().equals("") || address.getText().toString().equals("") || xiangxi.getText().toString().equals("")){
                    Toast.makeText(this,"输入有空",Toast.LENGTH_LONG).show();
                }else{
                    Gson gson = new Gson();
                    Address addressBean = new Address(id,name.getText().toString(),phone.getText().toString(),address.getText().toString(),xiangxi.getText().toString(),tag);
                    String s = gson.toJson(addressBean);
                    mPresenter.lreAll(s, ApiDoman.ADD_ADDRESS);
                }
                break;
            case R.id.add_back_txt:
                finish();
                break;
            case R.id.add_home:
                home.setChecked(true);
                tag = "家";
                break;
            case R.id.add_xuexiao:
                xuexiao.setChecked(true);
                tag = "学校";
                break;
            case R.id.add_gongsi:
                gongsi.setChecked(true);
                tag = "公司";
                break;
            case R.id.add_qita:
                qita.setChecked(true);
                tag = "其他";
                break;
        }
    }

}
