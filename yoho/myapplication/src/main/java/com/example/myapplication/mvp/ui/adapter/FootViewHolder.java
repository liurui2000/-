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

public class FootViewHolder extends RecyclerView.ViewHolder {
     public ImageView imageView;
        public TextView item_title;
        public TextView item_money;
        public TextView item_money2;
        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.item_pics);
            this.item_title = itemView.findViewById(R.id.item_titles);
            this.item_money = itemView.findViewById(R.id.item_moneys);
            this.item_money2 = itemView.findViewById(R.id.item_moneys2);
}
}
