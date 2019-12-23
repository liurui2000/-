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
import com.example.myapplication.mvp.model.entity.ShoneRecyclerBean;
import com.example.myapplication.mvp.model.entity.StaggBean;
import com.example.myapplication.mvp.ui.activity.WebViewActivity;

import java.util.ArrayList;

public class RecyclerShoneAdpater extends RecyclerView.Adapter<RecyclerShoneAdpater.baseview> {

    ArrayList<ShoneRecyclerBean> list;
    Context context;

    public RecyclerShoneAdpater(ArrayList<ShoneRecyclerBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_stagglayout3, parent, false);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        holder.textView.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getPic()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("uri","https://www.baidu.com/");
                intent.putExtra("ispa",2);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class baseview extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public baseview(@NonNull View itemView) {
            super(itemView);
            this.imageView= itemView.findViewById(R.id.stagg_image3);
            this.textView= itemView.findViewById(R.id.stagg_text3);
        }
    }
}
