package com.example.myapplication.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
import com.example.myapplication.mvp.model.entity.CouponEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.ui.adapter.CouponAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CouponActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {

    @BindView(R.id.cou_rec)
    RecyclerView couRec;
    @BindView(R.id.cou_back)
    ImageView couBack;
    private CouponAdapter couponAdapter;

    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.COUPON_LIST) {
            CouponEntity couponEntity = (CouponEntity) entity;
            List<CouponEntity.ValuesBean> values = couponEntity.getValues();

            couponAdapter = new CouponAdapter(values);
            couRec.setAdapter(couponAdapter);
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
        DaggerLreComponent.builder().lreModule(new LreModule(this))
                .appComponent(appComponent).build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_coupon;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        couRec.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.lreAll(ApiDoman.joint("user_id", "1"), ApiDoman.COUPON_LIST);
        couBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        couponAdapter = null;
    }
}
