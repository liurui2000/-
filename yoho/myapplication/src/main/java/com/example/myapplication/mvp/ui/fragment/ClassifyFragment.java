package com.example.myapplication.mvp.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;

import com.example.myapplication.mvp.ui.fragment.child.Classify_BrandFragment;
import com.example.myapplication.mvp.ui.fragment.child.Classify_CategoryFragment;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ClassifyFragment extends BaseFragment {

    @BindView(R.id.classify_tab)
    public TabLayout classify_tab;

    @BindView(R.id.classify_viewpager)
    public ViewPager classify_viewpager;

    private Unbinder bind;
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_classify_layout, null);
        bind = ButterKnife.bind(this, inflate);


        init();

        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = new Intent();
        intent.setAction("mars.liu");
        intent.putExtra("num",1);
        getActivity().sendBroadcast(intent);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


    }

    @Override
    public void setData(@Nullable Object data) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    private void init() {
        title.clear();
        fragments.clear();

        title.add("品类");
        title.add("品牌");
        fragments.add(new Classify_CategoryFragment());
        fragments.add(new Classify_BrandFragment());
        classify_tab.setSelectedTabIndicatorColor(Color.WHITE);
        classify_viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title.get(position);
            }
        });


        classify_tab.setupWithViewPager(classify_viewpager);

    }

}
