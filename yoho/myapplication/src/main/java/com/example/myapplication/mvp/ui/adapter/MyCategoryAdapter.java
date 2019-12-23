package com.example.myapplication.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MyCategoryAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> lists;
    private ArrayList<Boolean> booleans;

    public MyCategoryAdapter(Context context, ArrayList<String> lists, ArrayList<Boolean> booleans) {
        this.context = context;
        this.lists = lists;
        this.booleans = booleans;
    }
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = View.inflate(context, R.layout.category_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.item_View = view.findViewById(R.id.item_view);
            viewHolder.textView = view.findViewById(R.id.item_tv);
            viewHolder.linearLayout = view.findViewById(R.id.linear);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }






        viewHolder.textView.setText(lists.get(i));
        Boolean aBoolean = booleans.get(i);
        if (aBoolean == false){
            viewHolder.item_View.setVisibility(View.GONE);
            viewHolder.textView.setTextSize(15);
        }else {
            viewHolder.item_View.setVisibility(View.VISIBLE);
            viewHolder.textView.setTextSize(18);
        }


        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onclick(i,viewHolder.textView,booleans);
            }
        });

        return view;
    }
    class ViewHolder{
        View item_View;
        TextView textView;
        LinearLayout linearLayout;
    }
}
