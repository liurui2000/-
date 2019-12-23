package com.example.myapplication.mvp.ui.fragment.child;

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
import com.example.myapplication.doman.IOnTouchEvent;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.Brand_AllEntity;
import com.example.myapplication.mvp.model.entity.ShoesEnitty;
import com.example.myapplication.mvp.model.entity.ShoneRecyclerBean;
import com.example.myapplication.mvp.model.entity.StaggBean;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.ui.activity.WebViewActivity;
import com.example.myapplication.mvp.ui.adapter.RecyclerLowAllAdpater;
import com.example.myapplication.mvp.ui.adapter.RecyclerLowShoneAdpater;
import com.example.myapplication.mvp.ui.adapter.RecyclerShoneAdpater;
import com.example.myapplication.mvp.ui.adapter.RecyclerViewAdpater;
import com.example.myapplication.view.MyScorllRight;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 分类页面
 *  子页面
 *  品牌
 * */
public class Classify_BrandFragment extends BaseFragment<LrePresenter> implements LreContact.LreView {
    @BindView(R.id.brand_top_tab)
    public TabLayout brand_top_tab;

    @BindView(R.id.brand_banner)
    public Banner brand_banner;

    @BindView(R.id.brand_recyclerView)
    public RecyclerView brand_recyclerView;

    @BindView(R.id.brand_low_tab)
    public TabLayout brand_low_tab;

    @BindView(R.id.brand_low_recycler)
    public RecyclerView brand_low_recycler;

    @BindView(R.id.brand_scrollright)
    public MyScorllRight brand_scrollright;

    @BindView(R.id.brand_appbarlayout)
    public AppBarLayout brand_appbarlayout;

    private ArrayList<String> top_tab = new ArrayList<>();
    private ArrayList<String> low_tab = new ArrayList<>();
    private ArrayList<Integer> bannerList = new ArrayList<>();
    private ArrayList<ShoneRecyclerBean> recyclerList = new ArrayList<>();
    private Unbinder bind;
    private RecyclerShoneAdpater recyclerViewAdpater;
    private ArrayList<ShoneRecyclerBean> HotList = new ArrayList<>();
    private ArrayList<ShoneRecyclerBean> NotHostList = new ArrayList<>();
    private ArrayList<BrandListEntity.ValuesBean> BrandList = new ArrayList<>();
    private ArrayList<Brand_AllEntity> AllList = new ArrayList<>();
    private RecyclerLowShoneAdpater lowViewAdpater;
    private int flag = 0;
    private RecyclerLowAllAdpater recyclerLowAllAdpater;


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent).lreModule(new LreModule(this))
                .build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_child_brand_layout, null);

        bind = ButterKnife.bind(this, inflate);
        init();
        return inflate;
    }



    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        JSONObject object=new JSONObject();
        try {
            object.put("page","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.SHOES_LIST);

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.SHOES_LIST){
            if (entity instanceof ShoesEnitty){
                ShoesEnitty shoesEnitty = (ShoesEnitty) entity;
                List<ShoesEnitty.BrandBean> brand = shoesEnitty.getBrand();
                recyclerList.clear();

                for (int i = 0; i < brand.size(); i++) {
                    ShoneRecyclerBean shoneRecyclerBean = new ShoneRecyclerBean(Api.APP_DOMAIN + brand.get(i).getBrand_icon(), brand.get(i).getBrand_name());
                    recyclerList.add(shoneRecyclerBean);
                }
                recyclerViewAdpater.notifyDataSetChanged();

            }
        }else if (type == ApiDoman.BRAND_LIST){
            if (entity instanceof BrandListEntity){
                BrandListEntity brandListEntity = (BrandListEntity) entity;
                List<BrandListEntity.ValuesBean> values = brandListEntity.getValues();
                if (values!=null&&values.size()>0){
                    if (brand_low_recycler != null){
                        HotList.clear();
                        NotHostList.clear();
                        AllList.clear();

                        if (flag == 1){
                            for (int i = 0; i < values.size(); i++) {
                                if (values.get(i).getHot_tag().equals("true")){
                                    HotList.add(new ShoneRecyclerBean(Api.APP_DOMAIN+values.get(i).getBrand_bg(),values.get(i).getBrand_name()));
                                }
                            }
                            lowViewAdpater = new RecyclerLowShoneAdpater(HotList,getContext());
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                            brand_low_recycler.setAdapter(lowViewAdpater);
                            brand_low_recycler.setLayoutManager(gridLayoutManager);
                            lowViewAdpater.notifyDataSetChanged();

                        }else if (flag == 2){
                            for (int i = 0; i < values.size(); i++) {
                                if (values.get(i).getHot_tag().equals("false")){
                                    NotHostList.add(new ShoneRecyclerBean(Api.APP_DOMAIN+values.get(i).getBrand_bg(),values.get(i).getBrand_name()));
                                }
                            }
                            lowViewAdpater = new RecyclerLowShoneAdpater(NotHostList,getContext());
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                            brand_low_recycler.setAdapter(lowViewAdpater);
                            brand_low_recycler.setLayoutManager(gridLayoutManager);
                            lowViewAdpater.notifyDataSetChanged();
                        }else if (flag == 3){
                            BrandList.clear();
                            BrandList.addAll(values);
                            Collections.sort(BrandList, new Comparator<BrandListEntity.ValuesBean>() {
                                @Override
                                public int compare(BrandListEntity.ValuesBean t2, BrandListEntity.ValuesBean t1) {
                                    return t2.getBrand_letter().compareTo(t1.getBrand_letter());
                                }
                            });
                            String m = "";
                            for (int i = 0; i < BrandList.size(); i++) {
                                if (!BrandList.get(i).getBrand_letter().equals(m)){
                                    Brand_AllEntity brand_allEntity = new Brand_AllEntity();
                                    brand_allEntity.setTitle(BrandList.get(i).getBrand_letter());
                                    AllList.add(brand_allEntity);

                                    Brand_AllEntity brand_allEntity1 = new Brand_AllEntity();
                                    brand_allEntity1.setTitle("");
                                    brand_allEntity1.setBrand_bg(BrandList.get(i).getBrand_bg());
                                    brand_allEntity1.setBrand_letter(BrandList.get(i).getBrand_letter());
                                    brand_allEntity1.setBrand_name(BrandList.get(i).getBrand_name());
                                    brand_allEntity1.setHot_tag(BrandList.get(i).getHot_tag());
                                    AllList.add(brand_allEntity1);

                                }else {
                                    Brand_AllEntity brand_allEntity = new Brand_AllEntity();
                                    brand_allEntity.setTitle("");
                                    brand_allEntity.setBrand_bg(BrandList.get(i).getBrand_bg());
                                    brand_allEntity.setBrand_letter(BrandList.get(i).getBrand_letter());
                                    brand_allEntity.setBrand_name(BrandList.get(i).getBrand_name());
                                    brand_allEntity.setHot_tag(BrandList.get(i).getHot_tag());
                                    AllList.add(brand_allEntity);
                                    m =BrandList.get(i).getBrand_letter();
                                }

                            }
                            recyclerLowAllAdpater = new RecyclerLowAllAdpater(AllList, getContext());
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            brand_low_recycler.setAdapter(recyclerLowAllAdpater);
                            brand_low_recycler.setLayoutManager(linearLayoutManager);
                            recyclerLowAllAdpater.notifyDataSetChanged();


                        }


                    }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    private void init() {

        JSONObject object=new JSONObject();
        try {
            object.put("menuid","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(),ApiDoman.BRAND_LIST);
        flag = 3;


        top_tab.clear();
        top_tab.add("男装"); top_tab.add("女装"); top_tab.add("生活方式");
        top_tab.add("潮童"); top_tab.add("高街BLK");
        brand_top_tab.setSelectedTabIndicatorHeight(0);
        for (int i = 0; i < top_tab.size(); i++) {
            LinearLayout linearLayout = (LinearLayout) brand_top_tab.getChildAt(0);
            linearLayout.setDividerPadding(45);
            linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                    R.drawable.layout_divider_vertical));
            brand_top_tab.addTab(brand_top_tab.newTab().setText(top_tab.get(i)));
        }
        brand_top_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
                if (text.equals("男装")){
                    brand_banner.releaseBanner();
                    bannerList.clear();
                    bannerList.add(R.mipmap.brand1_banner1);
                    bannerList.add(R.mipmap.brand1_banner2);
                    bannerList.add(R.mipmap.brand1_banner3);
                    brand_banner.setImages(bannerList);
                    brand_banner.start();

                }else if (text.equals("女装")){
                    brand_banner.releaseBanner();
                    bannerList.clear();
                    bannerList.add(R.mipmap.nv_1);
                    bannerList.add(R.mipmap.nv_2);
                    bannerList.add(R.mipmap.nv_3);
                    brand_banner.setImages(bannerList);
                    brand_banner.start();

                }else if (text.equals("生活方式")){
                    brand_banner.releaseBanner();
                    bannerList.clear();
                    bannerList.add(R.mipmap.life_1);
                    bannerList.add(R.mipmap.life_2);
                    brand_banner.setImages(bannerList);
                    brand_banner.start();

                }else if (text.equals("潮童")){
                    brand_banner.releaseBanner();
                    bannerList.clear();
                    bannerList.add(R.mipmap.kids_1);
                    bannerList.add(R.mipmap.kids_2);
                    bannerList.add(R.mipmap.kids_3);
                    brand_banner.setImages(bannerList);
                    brand_banner.start();

                }else{
                    brand_banner.releaseBanner();
                    bannerList.clear();
                    bannerList.add(R.mipmap.blk_1);
                    bannerList.add(R.mipmap.blk_2);
                    bannerList.add(R.mipmap.blk_3);
                    brand_banner.setImages(bannerList);
                    brand_banner.start();

                }

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        bannerList.clear();
        bannerList.add(R.mipmap.brand1_banner1);
        bannerList.add(R.mipmap.brand1_banner2);
        bannerList.add(R.mipmap.brand1_banner3);
        brand_banner.setImages(bannerList);
        brand_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        brand_banner.setDelayTime(1500);
        brand_banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        brand_banner.start();

        recyclerViewAdpater = new RecyclerShoneAdpater(recyclerList, getContext());
        brand_recyclerView.setAdapter(recyclerViewAdpater);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        brand_recyclerView.setLayoutManager(linearLayoutManager);

        low_tab.clear();
        low_tab.add("全部品牌"); low_tab.add("新入驻品牌"); low_tab.add("热门品牌");
        for (int i = 0; i < low_tab.size(); i++) {
            LinearLayout linearLayout = (LinearLayout) brand_low_tab.getChildAt(0);
            linearLayout.setDividerPadding(45);
            linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                    R.drawable.layout_divider_vertical));
            brand_low_tab.addTab(brand_low_tab.newTab().setText(low_tab.get(i)));
        }
        brand_low_tab.setSelectedTabIndicatorHeight(0);
        brand_low_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String  text = tab.getText().toString();
                if (text.equals("全部品牌")){
                    JSONObject object=new JSONObject();
                    try {
                        object.put("menuid","1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mPresenter.lreAll(object.toString(),ApiDoman.BRAND_LIST);
                    flag = 3;
                    brand_scrollright.setVisibility(View.VISIBLE);

                }else if (text.equals("新入驻品牌")){
                    JSONObject object=new JSONObject();
                    try {
                        object.put("menuid","1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mPresenter.lreAll(object.toString(),ApiDoman.BRAND_LIST);
                    flag = 2;
                    brand_scrollright.setVisibility(View.GONE);
                }else {
                    JSONObject object=new JSONObject();
                    try {
                        object.put("menuid","1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mPresenter.lreAll(object.toString(),ApiDoman.BRAND_LIST);
                    flag = 1;
                    brand_banner.setVisibility(View.GONE);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        brand_scrollright.setTouchEvent(new IOnTouchEvent() {
            @Override
            public void onTouchEvent(int position) {
                char c = (char) (position+65);
                String txt = String.valueOf(c);
                for (int i = 0; i < AllList.size(); i++) {
                    if (!AllList.get(i).getTitle().equals("")&&AllList.get(i).getTitle().equals(txt)){
                        if (recyclerLowAllAdpater!=null){
                            Log.e("lr",c+":"+i);
                            brand_appbarlayout.setExpanded(false);
                            ((LinearLayoutManager) brand_low_recycler.getLayoutManager()).scrollToPositionWithOffset(i,0);

                        }
                    }
                }

            }
        });

        brand_banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("uri","https://www.baidu.com/");
                intent.putExtra("ispa",1);
                startActivity(intent);
            }
        });
    }
}
