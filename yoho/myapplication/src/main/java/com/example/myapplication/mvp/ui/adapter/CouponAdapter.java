package com.example.myapplication.mvp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.CouponEntity;

import java.util.List;

/**
 * @author ：created by
 * Create Date ：2019/12/20 10
 * Package_Name : yoho
 */
public class CouponAdapter extends RecyclerView.Adapter<CouponHolder> {
    public CouponAdapter(List<CouponEntity.ValuesBean> values) {
        this.values = values;
    }

    private List<CouponEntity.ValuesBean> values;

    @NonNull
    @Override
    public CouponHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_item, parent, false);
        return new CouponHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponHolder holder, int position) {
        holder.openBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    holder.layout.setVisibility(View.VISIBLE);
                }else {
                    holder.layout.setVisibility(View.GONE);
                }
            }
        });
        holder.num.setText("￥"+values.get(position).getCoupon_condition());
        holder.name.setText(values.get(position).getCoupon_title());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}

class CouponHolder extends RecyclerView.ViewHolder{

    LinearLayout layout;
    TextView name;
    TextView num;
    CheckBox openBox;
    public CouponHolder(@NonNull View itemView) {
        super(itemView);

        layout = itemView .findViewById(R.id.cou_product);
        num = itemView.findViewById(R.id.cou_num);
        name = itemView.findViewById(R.id.cou_name);
        openBox = itemView.findViewById(R.id.cou_open_close);
    }
}
