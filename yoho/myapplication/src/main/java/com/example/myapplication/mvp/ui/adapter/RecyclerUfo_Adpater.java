package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.Brand_AllEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.ui.activity.WebViewActivity;

import java.util.ArrayList;

public class RecyclerUfo_Adpater extends RecyclerView.Adapter<RecyclerUfo_Adpater.baseview> {

    ArrayList<GoodsListEntity.ValuesBean> list;
    Context context;

    public RecyclerUfo_Adpater(ArrayList<GoodsListEntity.ValuesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.ufo_item_layout, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {

        String goods_img_path = list.get(position).getGoods_img_path();
        Glide.with(context).load(Api.APP_DOMAIN+goods_img_path).into(holder.imageView);
        String goods_img_id = list.get(position).getGoods_img_id();
        if (goods_img_id.contains("0")||goods_img_id.contains("2")||
                goods_img_id.contains("4")||goods_img_id.contains("6")||
                goods_img_id.contains("8")){
            holder.ufo_item_rexiao.setVisibility(View.VISIBLE);
        }else {
            holder.ufo_item_rexiao.setVisibility(View.GONE);
        }

        holder.money.setText("￥"+list.get(position).getGoods_original_price());
        holder.text.setText(list.get(position).getGoods_name());
        holder.buy.setText(list.get(position).getGoods_discount_price()+"人购买");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("uri","https://www.baidu.com/");
                intent.putExtra("ispa",0);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class baseview extends RecyclerView.ViewHolder{
        TextView money;
        TextView buy;
        TextView text;
        ImageView imageView;
        ImageView ufo_item_rexiao;

        public baseview(@NonNull View itemView) {
            super(itemView);
            this.ufo_item_rexiao = itemView.findViewById(R.id.ufo_item_rexiao);
            this.money= itemView.findViewById(R.id.ufo_item_money);
            this.buy= itemView.findViewById(R.id.ufo_item_buy);
            this.text= itemView.findViewById(R.id.ufo_item_text);
            this.imageView= itemView.findViewById(R.id.ufo_item_img);
        }
    }
}
