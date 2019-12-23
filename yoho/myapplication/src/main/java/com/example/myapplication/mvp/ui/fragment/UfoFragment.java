package com.example.myapplication.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.SeeListEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.ui.adapter.RecyclerUfo_Adpater;
import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.stx.xhb.xbanner.XBanner;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UfoFragment extends BaseFragment<LrePresenter> implements LreContact.LreView {
    @BindView(R.id.ufo_tab)
    public TabLayout ufo_tab;

    @BindView(R.id.ufo_recycler)
    public XRecyclerView ufo_recycler;

    private Unbinder bind;
    private ArrayList<String> tabList = new ArrayList<>();
    private ArrayList<GoodsListEntity.ValuesBean> goodsList = new ArrayList<>();
    private ArrayList<Integer> bannerList = new ArrayList<>();
    private RecyclerUfo_Adpater recyclerUfo_adpater;
    private XBanner head_banner;
    private ImageView ufo_head_horiz;
    private ImageView ufo_head_low;


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent).lreModule(new LreModule(this))
                .build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_ufo_layout, null);
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
        JSONObject object=new JSONObject();
        try {
            object.put("category","1");
            object.put("page","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.GOODS_LIST);



    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.GOODS_LIST){
            if (entity instanceof GoodsListEntity){
                goodsList.clear();
                GoodsListEntity goodsListEntity = (GoodsListEntity) entity;
                List<GoodsListEntity.ValuesBean> values = goodsListEntity.getValues();
                goodsList.addAll(values);
                if (recyclerUfo_adpater!=null){
                    recyclerUfo_adpater.notifyDataSetChanged();
                }
            }
        }

    }

    @Override
    public void error(String error) {

    }

    @Override
    public void refreshSuccess(BaseEntity entity) {

    }

    @Override
    public void loadSuceess(BaseEntity entity) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }


    private void init() {
        tabList.clear();
        tabList.add("推荐"); tabList.add("新品"); tabList.add("人气"); tabList.add("潮搭");
        tabList.add("配饰"); tabList.add("实战"); tabList.add("女神");
        ufo_tab.setSelectedTabIndicatorHeight(0);
        for (int i = 0; i < tabList.size(); i++) {
            LinearLayout linearLayout = (LinearLayout) ufo_tab.getChildAt(0);
            linearLayout.setDividerPadding(45);
            linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                    R.drawable.layout_divider_vertical));
            ufo_tab.addTab(ufo_tab.newTab().setText(tabList.get(i)));
        }

        ufo_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = new TextView(getActivity());
                float selectedSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 16, getResources().getDisplayMetrics());
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,selectedSize);
                textView.setTextColor(Color.BLACK);
                textView.setText(tab.getText());
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                tab.setCustomView(textView);


                String text = tab.getText().toString();
                if (head_banner!=null&&ufo_head_horiz!=null&&ufo_head_low!=null){
                    if (text.equals("推荐")){

                        head_banner.setVisibility(View.VISIBLE);
                        ufo_head_horiz.setVisibility(View.VISIBLE);
                        ufo_head_low.setVisibility(View.VISIBLE);
                        head_banner.startAutoPlay();
                        Glide.with(getContext()).load(R.mipmap.ufo_img1).into(ufo_head_horiz);
                        Glide.with(getContext()).load(R.mipmap.ufo_img10).into(ufo_head_low);
                        ((LinearLayoutManager) ufo_recycler.getLayoutManager()).scrollToPositionWithOffset(0,0);
                    }else if (text.equals("新品")){
                        head_banner.setVisibility(View.GONE);
                        ufo_head_horiz.setVisibility(View.GONE);
                        ufo_head_low.setVisibility(View.GONE);
                        head_banner.stopAutoPlay();
                        ((LinearLayoutManager) ufo_recycler.getLayoutManager()).scrollToPositionWithOffset(0,0);
                    }else if (text.equals("人气")){
                        head_banner.setVisibility(View.GONE);
                        ufo_head_low.setVisibility(View.GONE);
                        ufo_head_horiz.setVisibility(View.VISIBLE);
                        head_banner.stopAutoPlay();
                        Glide.with(getContext()).load(R.mipmap.ufo_img7).into(ufo_head_horiz);
                        ((LinearLayoutManager) ufo_recycler.getLayoutManager()).scrollToPositionWithOffset(0,0);
                    }else if (text.equals("潮搭")){
                        head_banner.setVisibility(View.GONE);
                        ufo_head_low.setVisibility(View.GONE);
                        ufo_head_horiz.setVisibility(View.VISIBLE);
                        head_banner.stopAutoPlay();
                        Glide.with(getContext()).load(R.mipmap.ufo_img8).into(ufo_head_horiz);
                        ((LinearLayoutManager) ufo_recycler.getLayoutManager()).scrollToPositionWithOffset(0,0);

                    }else if (text.equals("配饰")){
                        head_banner.setVisibility(View.GONE);
                        ufo_head_low.setVisibility(View.GONE);
                        ufo_head_horiz.setVisibility(View.VISIBLE);
                        head_banner.stopAutoPlay();
                        Glide.with(getContext()).load(R.mipmap.ufo_img9).into(ufo_head_horiz);
                        ((LinearLayoutManager) ufo_recycler.getLayoutManager()).scrollToPositionWithOffset(0,0);
                    }else if (text.equals("实战")){
                        head_banner.setVisibility(View.GONE);
                        ufo_head_horiz.setVisibility(View.GONE);
                        ufo_head_low.setVisibility(View.GONE);
                        head_banner.stopAutoPlay();
                        ((LinearLayoutManager) ufo_recycler.getLayoutManager()).scrollToPositionWithOffset(0,0);
                    }else if (text.equals("女神")){
                        head_banner.setVisibility(View.GONE);
                        ufo_head_horiz.setVisibility(View.GONE);
                        ufo_head_low.setVisibility(View.GONE);
                        head_banner.stopAutoPlay();
                        ((LinearLayoutManager) ufo_recycler.getLayoutManager()).scrollToPositionWithOffset(0,0);
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        recyclerUfo_adpater = new RecyclerUfo_Adpater(goodsList, getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        ufo_recycler.setAdapter(recyclerUfo_adpater);
        ufo_recycler.setLayoutManager(gridLayoutManager);


        View head = View.inflate(getContext(), R.layout.ufo_head_layout, null);
        head_banner = head.findViewById(R.id.ufo_head_banner);
        ufo_head_horiz = head.findViewById(R.id.ufo_head_horiz);
        ufo_head_low = head.findViewById(R.id.ufo_head_low);

        bannerList.clear();
        bannerList.add(R.mipmap.ufo_img2); bannerList.add(R.mipmap.ufo_img3);
        bannerList.add(R.mipmap.ufo_img4);
        bannerList.add(R.mipmap.ufo_img5); bannerList.add(R.mipmap.ufo_img6);

        head_banner.setAutoPlayAble(true);
        head_banner.setData(bannerList,null);
        head_banner.setAutoPalyTime(2000);
        head_banner.setPointsIsVisible(false);
        head_banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getContext()).load(bannerList.get(position)).into((ImageView) view);
            }
        });
        head_banner.startAutoPlay();

        ufo_recycler.addHeaderView(head);

        ufo_recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                ufo_recycler.refreshComplete();
                if (recyclerUfo_adpater!=null){
                    recyclerUfo_adpater.notifyDataSetChanged();
                }

            }

            @Override
            public void onLoadMore() {
                ufo_recycler.loadMoreComplete();
                if (recyclerUfo_adpater!=null){
                    recyclerUfo_adpater.notifyDataSetChanged();
                }

            }
        });

    }

}
