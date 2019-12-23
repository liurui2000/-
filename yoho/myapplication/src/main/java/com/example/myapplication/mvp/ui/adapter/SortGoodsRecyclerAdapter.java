package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.CategoryGoodsEntity;
import com.example.myapplication.mvp.ui.activity.WebViewActivity;


import java.util.ArrayList;


public class SortGoodsRecyclerAdapter extends RecyclerView.Adapter<SortGoodsRecyclerAdapter.SortGoodsViewHolder> {
    ArrayList<CategoryGoodsEntity.ValuesBean> list;
    Context context;

    public SortGoodsRecyclerAdapter(ArrayList<CategoryGoodsEntity.ValuesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SortGoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new SortGoodsViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_sort_goods,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SortGoodsViewHolder holder, int position) {

            Glide.with(context).load(Api.APP_DOMAIN+list.get(position).getImage_path()).into(holder.img);
            holder.text_name.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("uri","https://www.baidu.com/");
                intent.putExtra("ispa",1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SortGoodsViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView text_name;
        public SortGoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_adapter_sort_goods_img);
            text_name = itemView.findViewById(R.id.iv_adapter_sort_goods_name);
        }

    }

}
