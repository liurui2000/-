package com.example.myapplication.mvp.ui.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class SqlViewHolder extends RecyclerView.ViewHolder {
    public CheckBox checkBox;
    public ImageView imageView;
    public TextView item_title;
    public TextView item_money;
    public Button bnt_reudce;
    public Button bnt_add;
    public TextView item_num;
    public LinearLayout linears;

    public SqlViewHolder(@NonNull View itemView) {
        super(itemView);
        this.linears = itemView.findViewById(R.id.linears);
        this.checkBox = itemView.findViewById(R.id.item_checkd);
        this.imageView = itemView.findViewById(R.id.item_pic);
        this.item_title = itemView.findViewById(R.id.item_title);
        this.item_money = itemView.findViewById(R.id.item_money);
        this.bnt_reudce = itemView.findViewById(R.id.item_reduce);
        this.bnt_add = itemView.findViewById(R.id.item_add);
        this.item_num = itemView.findViewById(R.id.item_num);
    }
}
