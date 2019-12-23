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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.CommunityEntity;
import com.example.myapplication.mvp.model.entity.StaggBean;
import com.example.myapplication.mvp.ui.activity.WebViewActivity;

import java.util.ArrayList;

public class BannercommunityAdpater extends RecyclerView.Adapter<BannercommunityAdpater.baseview> {

    ArrayList<CommunityEntity.BannerBean> list;
    Context context;

    public BannercommunityAdpater(ArrayList<CommunityEntity.BannerBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.layout_stagglayout4, null);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        Glide.with(context)
                .load(Api.APP_DOMAIN+list.get(position).getRecommend_url())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(holder.imageView);
        holder.textView.setText(list.get(position).getRecommend_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("uri","https://www.baidu.com/");
                intent.putExtra("ispa",3);
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
            this.imageView= itemView.findViewById(R.id.stagg_image5);
            this.textView= itemView.findViewById(R.id.stagg_text5);
        }
    }
}
