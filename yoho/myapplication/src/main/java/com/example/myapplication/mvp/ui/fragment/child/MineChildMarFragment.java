package com.example.myapplication.mvp.ui.fragment.child;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.mvp.ui.activity.AddressActivity;
import com.example.myapplication.mvp.ui.activity.CouponActivity;
import com.example.myapplication.mvp.ui.activity.FootprintActivity;
import com.example.myapplication.mvp.ui.activity.ShowDdActivity;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;

/**
 * @author ：created by
 * Create Date ：2019/12/18 09
 * Package_Name : yoho
 */
public class MineChildMarFragment extends BaseFragment {

    TextView mine_shopping_all_order;
    LinearLayout child_address;
    LinearLayout child_liulan;
    TextView foot_num;
    LinearLayout youhui;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.mine_right_child_frag, container, false);
        mine_shopping_all_order = inflate.findViewById(R.id.mine_shopping_all_order);
        child_address = inflate.findViewById(R.id.child_address);
        child_liulan = inflate.findViewById(R.id.child_liulan);
        foot_num = inflate.findViewById(R.id.foot_num);
        youhui = inflate.findViewById(R.id.youhui);
        foot_num.setText("10"+"");
        youhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CouponActivity.class));
            }
        });

        child_liulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FootprintActivity.class));
            }
        });


        child_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddressActivity.class));
            }
        });
        mine_shopping_all_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ShowDdActivity.class));
            }
        });
        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
