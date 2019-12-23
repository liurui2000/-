package com.example.myapplication.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.alipay.PayDemoActivity;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.CarListEntity;
import com.example.myapplication.mvp.model.entity.SqlBean;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.ui.adapter.SqlViewAdapter;
import com.example.myapplication.mvp.ui.adapter.SqlViewHolder;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShoppingCarActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {

    @BindView(R.id.iv_shopcar_goback)
    ImageView ivShopcarGoback;
    @BindView(R.id.iv_shopcar_goodslist_rcv)//列表
    RecyclerView det_list;
    @BindView(R.id.iv_shopcar_allselect)//全选
    CheckBox gwc_all;
    @BindView(R.id.iv_shopcar_total)//钱
    TextView gwc_mon;
    @BindView(R.id.iv_shopcar_num)//数量
    TextView ivShopcarNum;
    @BindView(R.id.iv_shopcar_pay)//结算
    Button ivShopcarPay;
    @BindView(R.id.iv_shopcar_money_buy)//总价
    LinearLayout ivShopcarMoneyBuy;
    private SqlViewAdapter adapter;//适配器
    private ArrayList<SqlBean> gwcList = new ArrayList<>();
    private int sumMoney = 0;
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().lreModule(new LreModule(this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_shopping_car;
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
        initListener();
    }

    private void initListener() {

        adapter = new SqlViewAdapter(gwcList,this) {
            @Override
            public void bind(SqlViewHolder holder, int position) {
                Glide.with(ShoppingCarActivity.this).load(gwcList.get(position).getPic()).apply(RequestOptions.bitmapTransform(new CenterCrop())).into(holder.imageView);
                holder.item_title.setText(gwcList.get(position).getTitle());
                holder.item_money.setText("尺码"+gwcList.get(position).getSize()+"\n\t颜色"+gwcList.get(position).getColor()+"\n\t单价:"+gwcList.get(position).getMoney());
                holder.item_num.setText(gwcList.get(position).getCount()+"");
                holder.checkBox.setChecked(gwcList.get(position).getCheck());


                holder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (holder.checkBox.isChecked()==true){
                            gwcList.get(position).setCheck(true);
                            boolean ff = true;
                            for (int i = 0; i < gwcList.size(); i++) {
                                if (gwcList.get(i).getCheck()==false){
                                    ff = false;
                                    break;
                                }
                            }
                            if (ff == true){
                                gwc_all.setChecked(true);
                            }else {
                                gwc_all.setChecked(false);
                            }
                            sumMoney+=gwcList.get(position).getMoney()*gwcList.get(position).getCount();
                            gwc_mon.setText("总计:"+sumMoney);
                            num = num+gwcList.get(position).getCount();
                            ivShopcarNum.setText(num+"");
                        }else {
                            gwcList.get(position).setCheck(false);
                            gwc_all.setChecked(false);
                            sumMoney-=gwcList.get(position).getMoney()*gwcList.get(position).getCount();
                            gwc_mon.setText("总计:"+sumMoney);
                            num = num-gwcList.get(position).getCount();
                            ivShopcarNum.setText(num+"");
                        }
                    }
                });

                holder.bnt_reudce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = gwcList.get(position).getCount();
                        count--;
                        if (count>0){
                            gwcList.get(position).setCount(count);
                            notifyDataSetChanged();
                            if (gwcList.get(position).getCheck()==true){
                                sumMoney-=gwcList.get(position).getMoney();
                                gwc_mon.setText("总计:"+sumMoney);
                                num = num-1;
                                ivShopcarNum.setText(num+"");
                            }
                        }
                    }
                });
                holder.bnt_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = gwcList.get(position).getCount();
                        count++;
                        if (count<30){
                            gwcList.get(position).setCount(count);
                            notifyDataSetChanged();
                            if (gwcList.get(position).getCheck()==true){
                                sumMoney+=gwcList.get(position).getMoney();
                                gwc_mon.setText("总计:"+sumMoney);
                                num = num+1;
                                ivShopcarNum.setText(num+"");
                            }
                        }
                    }
                });
            }
        };




        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        det_list.setAdapter(adapter);
        det_list.setLayoutManager(linearLayoutManager);

        ivShopcarGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gwc_all.setOnClickListener(new View.OnClickListener() {//全选
            @Override
            public void onClick(View v) {
                if (gwc_all.isChecked() == true){
                    sumMoney=0;
                    for (int i = 0; i < gwcList.size(); i++) {
                        gwcList.get(i).setCheck(true);
                        sumMoney+=gwcList.get(i).getMoney()*gwcList.get(i).getCount();
                        num+=gwcList.get(i).getCount();
                    }
                    ivShopcarNum.setText(num+"");
                    gwc_mon.setText("总计:"+sumMoney);
                    adapter.notifyDataSetChanged();
                }else {
                    for (int i = 0; i < gwcList.size(); i++) {
                        gwcList.get(i).setCheck(false);
                    }
                    adapter.notifyDataSetChanged();
                    sumMoney = 0;
                    num=0;
                    ivShopcarNum.setText(num+"");
                    gwc_mon.setText("总计:"+sumMoney);
                }
            }
        });

        //结算
        ivShopcarPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingCarActivity.this, PayDemoActivity.class));
            }
        });

    }

    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.CAR_LIST){
            if (entity instanceof CarListEntity){
                CarListEntity carListEntity = (CarListEntity) entity;
                List<CarListEntity.ValuesBean> values = carListEntity.getValues();
                gwcList.clear();
                for (int i = 0; i < values.size(); i++) {
                    SqlBean sqlBean = new SqlBean();
                    sqlBean.setCheck(false);
                    String shop_color = values.get(i).getShop_color();
                    if (shop_color.equals("1")){
                        sqlBean.setColor("红色");
                    }else if (shop_color.equals("2")){
                        sqlBean.setColor("绿色");
                    }else if (shop_color.equals("3")){
                        sqlBean.setColor("白色");
                    }else if (shop_color.equals("4")){
                        sqlBean.setColor("黑色");
                    }else {
                        sqlBean.setColor(values.get(i).getShop_color());
                    }
                    sqlBean.setCount((Integer.parseInt(values.get(i).getShop_num())));
                    sqlBean.setMoney((Integer.parseInt(values.get(i).getShop_price())));
                    sqlBean.setTitle(values.get(i).getShop_name());
                    sqlBean.setPic(Api.APP_DOMAIN+values.get(i).getCar_path());
                    sqlBean.setId(values.get(i).getCar_id());
                    sqlBean.setUserid(values.get(i).getUser_id());
                    sqlBean.setGoodsid(values.get(i).getGoods_id());
                    String shop_size = values.get(i).getShop_size();
                    if (shop_size.equals("1")){
                        sqlBean.setSize("L");
                    }else if (shop_size.equals("2")){
                        sqlBean.setSize("XL");
                    }else if (shop_size.equals("3")){
                        sqlBean.setSize("XXL");
                    }else if (shop_size.equals("4")){
                        sqlBean.setSize("XXXL");
                    }else {
                        sqlBean.setSize(shop_size);
                    }

                    gwcList.add(sqlBean);

                }
                if (adapter!=null){
                    adapter.notifyDataSetChanged();
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
