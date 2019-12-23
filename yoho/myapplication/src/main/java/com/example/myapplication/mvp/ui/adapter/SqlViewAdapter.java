package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.SqlBean;

import java.util.ArrayList;

public abstract class SqlViewAdapter extends RecyclerView.Adapter<SqlViewHolder> {
    private ArrayList<SqlBean> list;
    private Context context;

    public SqlViewAdapter(ArrayList<SqlBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SqlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.gwc_item_layout, null);
        return new SqlViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SqlViewHolder holder, int position) {
        bind(holder,position);
    }

    public abstract void bind(SqlViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return list.size();
    }
}
