package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.FootPrintEntity;
import com.example.myapplication.mvp.model.entity.SqlBean;

import java.util.ArrayList;

public class FootPrintViewAdapter extends RecyclerView.Adapter<FootViewHolder> {
    private ArrayList<FootPrintEntity.ValuesBean> list;
    private Context context;

    public FootPrintViewAdapter(ArrayList<FootPrintEntity.ValuesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FootViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.foot_item_layout, null);
        return new FootViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FootViewHolder holder, int position) {

        Glide.with(context).load(Api.APP_DOMAIN+list.get(position).getGoods_img_path()).into(holder.imageView);
        holder.item_title.setText(list.get(position).getGoods_name()+"\n\t"+list.get(position).getGoods_guarantee());
        holder.item_money.setText("￥"+list.get(position).getGoods_discount_price());
        holder.item_money2.setText("￥"+list.get(position).getGoods_original_price());
        holder.item_money2.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
