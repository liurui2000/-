package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.CommunityEntity;
import com.example.myapplication.mvp.model.entity.MessageEntity;
import com.example.myapplication.mvp.ui.activity.MessageHuiyuanActivity;
import com.example.myapplication.mvp.ui.activity.SerMesageCenterActivity;
import com.example.myapplication.mvp.ui.activity.WebViewActivity;

import java.util.ArrayList;
import java.util.List;


public class RecyclerMessage_Adpater extends RecyclerView.Adapter<RecyclerMessage_Adpater.baseview> {
    private ArrayList<MessageEntity> messageEntities;
    private Context context;

    public RecyclerMessage_Adpater(ArrayList<MessageEntity> messageEntities, Context context) {
        this.messageEntities = messageEntities;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.message_item_layout, null);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {

        if (!messageEntities.get(position).getString2().equals("")){
            holder.textView2.setText(messageEntities.get(position).getString2());
        }
        Glide.with(context).load(messageEntities.get(position).getImg()).into(holder.imageView);
        holder.textView1.setText(messageEntities.get(position).getString1());
        holder.textView3.setText(messageEntities.get(position).getString3());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (messageEntities.get(position).getString1().equals("内容消息")){
                    context.startActivity(new Intent(context, SerMesageCenterActivity.class));
                }else {
                    Intent intent = new Intent(context, MessageHuiyuanActivity.class);
                    intent.putExtra("title",messageEntities.get(position).getString1());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageEntities.size();
    }

    class baseview extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        public baseview(@NonNull View itemView) {
            super(itemView);
           this.imageView = itemView.findViewById(R.id.message_item_iv);
            this.textView1 = itemView.findViewById(R.id.message_item_tv1);
            this.textView2 = itemView.findViewById(R.id.message_item_tv2);
            this.textView3 = itemView.findViewById(R.id.message_item_tv3);
        }
    }
}
