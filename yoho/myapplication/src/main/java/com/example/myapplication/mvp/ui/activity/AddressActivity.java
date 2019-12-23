package com.example.myapplication.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
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
import com.example.myapplication.mvp.model.entity.AddressEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;

public class AddressActivity extends BaseActivity<LrePresenter> implements LreContact.LreView, View.OnClickListener {

    @BindView(R.id.address_back_txt)
    TextView back;
    @BindView(R.id.address_rlist)
    RecyclerView recyclerView;
    @BindView(R.id.address_add_address)
    RelativeLayout relativeLayout;

    String id;
    CommonAdapter adapter;
    ArrayList<AddressEntity.ValuesBean> lists = new ArrayList<>();

    @Override
    public void success(BaseEntity baseEntity, int type) {
        if(type == ApiDoman.ADDRESSMANAGER){
            AddressEntity addressEntity = (AddressEntity) baseEntity;
            if(addressEntity != null){
                if(lists.size() > 0){
                    lists.clear();
                }
                lists.addAll(addressEntity.getValues());
                adapter.notifyDataSetChanged();
            }
        }else if(type == ApiDoman.DELETE_ADDRESS){
            mPresenter.lreAll(ApiDoman.joint("user_id",id),ApiDoman.ADDRESSMANAGER);
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
        return R.layout.activity_address_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initList();
        initOnClick();
        id = "1";
        getData();
    }

    private void getData() {
        mPresenter.lreAll(ApiDoman.joint("user_id",id),ApiDoman.ADDRESSMANAGER);
    }

    private void initList() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new CommonAdapter(this,R.layout.address_item,lists) {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {
                TextView name = holder.getView(R.id.address_name);
                TextView phone = holder.getView(R.id.address_phone);
                TextView address = holder.getView(R.id.address_address);
                TextView delete = holder.getView(R.id.address_delete);
                AddressEntity.ValuesBean bean = lists.get(position);
                name.setText(bean.getUser_name());
                phone.setText(bean.getPhone());
                address.setText(bean.getAddress_area());
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.lreAll(ApiDoman.joint("address_id",bean.getAddress_id()),ApiDoman.DELETE_ADDRESS);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    private void initOnClick() {
        back.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.address_add_address:
                Intent intent = new Intent(this, AddAddressActivity.class);
                intent.putExtra("id",id);
                startActivityForResult(intent,500);
                break;
            case R.id.address_back_txt:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 500 && resultCode == 505){
            mPresenter.lreAll(id,ApiDoman.ADDRESSMANAGER);
        }
    }
}
