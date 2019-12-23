package com.example.myapplication.mvp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.FootPrintEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.ui.adapter.FootPrintViewAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FootprintActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {


    @BindView(R.id.footprint_back)
    TextView footprintBack;
    @BindView(R.id.footprint_clear)
    TextView footprintClear;
    @BindView(R.id.footprint_list)
    RecyclerView footprintList;
    private ArrayList<FootPrintEntity.ValuesBean> list = new ArrayList<>();
    private FootPrintViewAdapter footPrintViewAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent)
                .lreModule(new LreModule(this))
                .build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_footprint;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        JSONObject object=new JSONObject();
        try {
            object.put("userid","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.FOOT_PRINT);

        footprintBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        footprintClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                if (footPrintViewAdapter!=null){
                    footPrintViewAdapter.notifyDataSetChanged();
                }
            }
        });

        footPrintViewAdapter = new FootPrintViewAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        footprintList.setAdapter(footPrintViewAdapter);
        footprintList.setLayoutManager(linearLayoutManager);


    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.FOOT_PRINT){
            if (entity instanceof FootPrintEntity){
                FootPrintEntity footPrintEntity = (FootPrintEntity) entity;
                List<FootPrintEntity.ValuesBean> values = footPrintEntity.getValues();
                list.clear();
                list.addAll(values);
                if (footPrintViewAdapter!=null){
                    footPrintViewAdapter.notifyDataSetChanged();
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
