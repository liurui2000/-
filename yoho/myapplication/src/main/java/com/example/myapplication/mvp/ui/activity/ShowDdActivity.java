package com.example.myapplication.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.CarListEntity;
import com.example.myapplication.mvp.model.entity.OrderEntity;
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
import butterknife.ButterKnife;

public class ShowDdActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {

    @BindView(R.id.dd_back_activity)
    ImageView ddBackActivity;
    @BindView(R.id.dd_recyclerView)
    RecyclerView ddRecyclerView;
    private SqlViewAdapter adapter;//适配器
    private ArrayList<SqlBean> gwcList = new ArrayList<>();
    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.ORDER_LIST){
            if (entity instanceof OrderEntity){
                OrderEntity orderEntity = (OrderEntity) entity;
                List<OrderEntity.ValuesBean> values = orderEntity.getValues();
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
                    sqlBean.setId(values.get(i).getGoods_id());
                    sqlBean.setUserid("");
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
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().lreModule(new LreModule(this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_show_dd;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        JSONObject object=new JSONObject();
        try {
            object.put("userid","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.ORDER_LIST);

        ddBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapter = new SqlViewAdapter(gwcList,this) {
            @Override
            public void bind(SqlViewHolder holder, int position) {
                Glide.with(ShowDdActivity.this).load(gwcList.get(position).getPic()).apply(RequestOptions.bitmapTransform(new CenterCrop())).into(holder.imageView);
                holder.item_title.setText(gwcList.get(position).getTitle());
                holder.item_money.setText("尺码"+gwcList.get(position).getSize()+"\n\t颜色"+gwcList.get(position).getColor()+"\n\t单价:"+gwcList.get(position).getMoney());
                holder.item_num.setText(gwcList.get(position).getCount()+"");
                holder.checkBox.setVisibility(View.GONE);
                holder.bnt_add.setVisibility(View.GONE);
                holder.bnt_reudce.setVisibility(View.GONE);

            }
        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ddRecyclerView.setAdapter(adapter);
        ddRecyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
