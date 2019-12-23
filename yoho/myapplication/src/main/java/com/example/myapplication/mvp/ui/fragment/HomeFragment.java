package com.example.myapplication.mvp.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerHomeComponent;
import com.example.myapplication.di.module.HomeModule;
import com.example.myapplication.mvp.contact.HomeContact;
import com.example.myapplication.mvp.model.entity.BannerEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.example.myapplication.mvp.model.entity.RecommendEntity;
import com.example.myapplication.mvp.presenter.HomePresenter;
import com.example.myapplication.mvp.ui.activity.MessageActivity;
import com.example.myapplication.mvp.ui.activity.SearchAcitvity;
import com.example.myapplication.mvp.ui.fragment.child.KidsFragment;
import com.example.myapplication.mvp.ui.fragment.child.LifeFragment;
import com.example.myapplication.mvp.ui.fragment.child.ManFragment;
import com.example.myapplication.mvp.ui.fragment.child.WomanFragment;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContact.HomeIView {
    //扫一扫
    @BindView(R.id.home_sao)
    ImageView home_sao;
    //搜索框
    @BindView(R.id.home_search)
    TextView home_search;
    //信息
    @BindView(R.id.home_message)
    ImageView home_message;
    //黄油刀
    Unbinder bind;
    //tablayout
    @BindView(R.id.home_tab)
    TabLayout home_tab;
    //viewpager
    @BindView(R.id.home_pager)
    ViewPager home_pager;


    private ArrayList<Fragment> fragments = new ArrayList<>();
    @Override
    public void onSuccess() {

    }

    @Override
    public void resultSuccess(BaseEntity entity, int type) {
        if (type==1){
            BannerEntity bannerEntity = (BannerEntity) entity;
            Log.e("bannerEntity",bannerEntity.toString());
        }else if (type==2){
            RecommendEntity recommendEntity = (RecommendEntity) entity;
            Log.e("recommendEntity",recommendEntity.toString());
        }else if (type==3){
            GoodsListEntity goodsListEntity = (GoodsListEntity) entity;
            Log.e("goodsListEntity",goodsListEntity.toString());
        }else if (type==4){
            MenuEntity menuEntity = (MenuEntity) entity;
            Log.e("menuEntity",menuEntity.toString());

            List<MenuEntity.ValuesBean> values = menuEntity.getValues();

            fragments.clear();
            fragments.add(new ManFragment());
            fragments.add(new WomanFragment());
            fragments.add(new KidsFragment());
            fragments.add(new LifeFragment());


            home_pager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

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
                    return values.get(position).getMenu_name();
                }
            });
            home_tab.setupWithViewPager(home_pager);
            home_tab.setSelectedTabIndicatorHeight(0);
            home_tab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    TextView textView = new TextView(getActivity());
                    float selectedSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 16, getResources().getDisplayMetrics());
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,selectedSize);
                    textView.setTextColor(Color.BLACK);
                    textView.setText(tab.getText());
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    tab.setCustomView(textView);

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    tab.setCustomView(null);

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        }
        home_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MessageActivity.class));
            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void gggg(String s){
        Log.e("lr",s);
        if(s.equals("tabzhan")){
            home_tab.setVisibility(View.VISIBLE);
        }else if(s.equals("tabzhe")){
            home_tab.setVisibility(View.GONE);
        }


    }

    @Override
    public void pulltoRefresh(BaseEntity baseEntity) {

    }

    @Override
    public void loadMore(BaseEntity entity) {

    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home_layout, null);
        bind = ButterKnife.bind(this, inflate);

        home_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchAcitvity.class));
            }
        });

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

    private void init() {
//        int id = home_search.getContext().getResources()
//                .getIdentifier("android:id/search_src_text", null, null);
//        TextView textView = (TextView) home_search.findViewById(id);
//        textView.setTextColor(Color.parseColor("#818080"));//字体颜色
//        textView.setTextSize(15);//字体、提示字体大小
//        textView.setGravity(Gravity.BOTTOM);
//        textView.setHintTextColor(Color.parseColor("#818080"));//提示字体颜色
//        android:iconifiedByDefault="false"
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }


        home_sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,103);
            }
        });

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.requestAlls();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 103){
            if (resultCode == Activity.RESULT_OK){
                String stringExtra = data.getStringExtra(CodeUtils.RESULT_STRING);
                Toast.makeText(getContext(), ""+stringExtra, Toast.LENGTH_SHORT).show();
                //生成不带logo的二维码
                // CodeUtils.createImage(toString, 200, 200, null)
                //生成带logo的二维码
                // CodeUtils.createImage(toString, 200, 200, BitmapFactory.decodeResource(getResources(), R.mipmap.b))
            }
        }
    }
}
