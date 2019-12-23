package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.myapplication.mvp.model.entity.Brand_AllEntity;
import com.example.myapplication.mvp.model.entity.ShoneRecyclerBean;
import com.example.myapplication.mvp.ui.activity.WebViewActivity;

import java.util.ArrayList;

public class RecyclerLowAllAdpater extends RecyclerView.Adapter<RecyclerLowAllAdpater.baseview> {

    ArrayList<Brand_AllEntity> list;
    Context context;

    public RecyclerLowAllAdpater(ArrayList<Brand_AllEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_brand_low_item, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        String getTitle = list.get(position).getTitle();
        if (!getTitle.equals("")){
            holder.top.setVisibility(View.VISIBLE);
            holder.low.setVisibility(View.GONE);
            holder.title.setText(list.get(position).getTitle());
        }else {
            holder.top.setVisibility(View.GONE);
            holder.low.setVisibility(View.VISIBLE);
            String hot_tag = list.get(position).getHot_tag();

            if (hot_tag.equals("true")){
                holder.hot.setVisibility(View.VISIBLE);
                holder.news.setVisibility(View.GONE);
            }else {
                holder.hot.setVisibility(View.GONE);
                holder.news.setVisibility(View.VISIBLE);
            }
            holder.string.setText(list.get(position).getBrand_name());
        }
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
    public int getItemCount() {
        return list.size();
    }

    class baseview extends RecyclerView.ViewHolder{

        TextView title;
        TextView string;
        TextView hot;
        TextView news;
        LinearLayout top;
        LinearLayout low;

        public baseview(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.brand_item_title);
            this.string = itemView.findViewById(R.id.brand_item_string);
            this.hot = itemView.findViewById(R.id.brand_item_hot);
            this.news = itemView.findViewById(R.id.brand_item_new);
            this.top = itemView.findViewById(R.id.linear_top);
            this.low = itemView.findViewById(R.id.linear_low);
        }
    }
}
