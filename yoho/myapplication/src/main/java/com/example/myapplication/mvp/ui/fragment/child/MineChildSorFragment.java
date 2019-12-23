package com.example.myapplication.mvp.ui.fragment.child;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

/**
 * @author ：created by
 * Create Date ：2019/12/18 09
 * Package_Name : yoho
 */
public class MineChildSorFragment extends BaseFragment {
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.mine_left_child_frag, container, false);
        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
