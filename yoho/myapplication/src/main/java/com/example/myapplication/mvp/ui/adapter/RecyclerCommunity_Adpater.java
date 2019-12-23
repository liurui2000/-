package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.util.Log;
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
import com.example.myapplication.mvp.ui.activity.WebViewActivity;

import java.util.ArrayList;
import java.util.List;


public class RecyclerCommunity_Adpater extends RecyclerView.Adapter<RecyclerCommunity_Adpater.baseview> {
    private ArrayList<CommunityEntity.ValuesBean> communityBean;
    private Context context;

    public RecyclerCommunity_Adpater(ArrayList<CommunityEntity.ValuesBean> communityBean, Context context) {
        this.communityBean = communityBean;
        this.context = context;
    }

    @NonNull
    @Override
    public baseview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.community_item_layout, null);
        return new baseview(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull baseview holder, int position) {
        if (position==2){
            holder.item_linear.setVisibility(View.GONE);
        }
        List<CommunityEntity.ValuesBean.ImgsBean> imgs = communityBean.get(position).getImgs();

        int height =(int)Math.floor((Math.random()*450)+400);
        ViewGroup.LayoutParams layoutParams = holder.item_iv.getLayoutParams();
        layoutParams.height = height;
        layoutParams.width = 520;
        holder.item_iv.setLayoutParams(layoutParams);

        if (imgs.size()>1){
            Glide.with(context).load(Api.APP_DOMAIN+imgs.get(0).getImg_path()+".jpg").into(holder.item_iv);
            Glide.with(context).load(Api.APP_DOMAIN+imgs.get(1).getImg_path()+".jpg").apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.item_photo);
        }else {
            Glide.with(context).load(Api.APP_DOMAIN+imgs.get(0).getImg_path()+".jpg").into(holder.item_iv);
            Glide.with(context).load(Api.APP_DOMAIN+imgs.get(0).getImg_path()+".jpg").apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.item_photo);
        }
        holder.item_name.setText(communityBean.get(position).getTitle());
        holder.item_tv.setText(communityBean.get(position).getCommunity_values()+communityBean.get(position).getTag()
        +communityBean.get(position).getTag());
        holder.item_count.setText(communityBean.get(position).getFabulous()+"");
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
        return communityBean.size();
    }

    class baseview extends RecyclerView.ViewHolder{
        public ImageView item_iv;
        public TextView item_tv;
        public ImageView item_photo;
        public TextView item_name;
        public TextView item_count;
        public LinearLayout item_linear;
        public baseview(@NonNull View itemView) {
            super(itemView);
            this.item_linear = itemView.findViewById(R.id.item_linear);
            this.item_iv = itemView.findViewById(R.id.item_iv);
            this.item_tv = itemView.findViewById(R.id.item_tv);
            this.item_photo = itemView.findViewById(R.id.item_photo);
            this.item_name = itemView.findViewById(R.id.item_name);
            this.item_count = itemView.findViewById(R.id.item_count);
        }
    }
}
