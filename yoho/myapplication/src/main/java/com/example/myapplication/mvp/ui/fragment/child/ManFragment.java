package com.example.myapplication.mvp.ui.fragment.child;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerHomeComponent;
import com.example.myapplication.di.module.HomeModule;
import com.example.myapplication.mvp.contact.HomeContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.BannerEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.example.myapplication.mvp.model.entity.RecommendEntity;
import com.example.myapplication.mvp.model.entity.StaggBean;
import com.example.myapplication.mvp.presenter.HomePresenter;
import com.example.myapplication.mvp.ui.activity.WebViewActivity;
import com.example.myapplication.mvp.ui.adapter.MyTabRecyclerAdpater;
import com.example.myapplication.mvp.ui.adapter.RecyclerViewAdpater;
import com.example.myapplication.mvp.ui.adapter.RecyclerViewAdpater2;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ManFragment extends BaseFragment<HomePresenter> implements HomeContact.HomeIView {
    public  ArrayList<String> bannerlist = new ArrayList<>();
    private ArrayList<Integer> lowbannerlist = new ArrayList<>();
    public  ArrayList<StaggBean> stagglist = new ArrayList<>();
    private ArrayList<StaggBean> lowstagglist = new ArrayList<>();
    public  Banner man_low_banner;
    public  Banner man_banner;
    public  NestedScrollView s;
    public  RecyclerView man_staggview;
    private Unbinder bind;
    private RecyclerView man_low_staggview;
    private TabLayout man_tab;
    private XRecyclerView man_recyclerview;
    private ArrayList<RecommendEntity.CategoryBean> category = new ArrayList<>();
    private ArrayList<GoodsListEntity.ValuesBean> goodslist = new ArrayList<>();
    private MyTabRecyclerAdpater myTabRecyclerAdpater;
    private String categoryString = "";
    private boolean flag = false;
    private AppBarLayout man_appbarlayout;
    private boolean flags = true;
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
        View inflate = inflater.inflate(R.layout.fragment_child_man_layout, null);
        bind = ButterKnife.bind(this, inflate);
        man_banner=inflate.findViewById(R.id.man_banner);
        man_staggview=inflate.findViewById(R.id.man_staggview);
        man_low_banner = inflate.findViewById(R.id.man_low_banner);
        man_low_staggview = inflate.findViewById(R.id.man_low_staggview);
        man_recyclerview = inflate.findViewById(R.id.man_recyclerview);
        man_tab = inflate.findViewById(R.id.man_tab);
        man_tab.setSelectedTabIndicatorColor(Color.BLACK);
        man_appbarlayout = inflate.findViewById(R.id.man_appbarlayout);

        man_appbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                int[] arr = new int[2];
                man_tab.getLocationOnScreen(arr);
//                Log.e("lr",arr[1]+"");
                if(arr[1] >= 306 && arr[1] <= 350 && flags){
                    flags = false;
                    EventBus.getDefault().post("tabzhe");
                }
                if(arr[1] >350 && !flags){
                    flags = true;
                    EventBus.getDefault().post("tabzhan");
                }

            }
        });


        stagglist.clear();

        stagglist.add(new StaggBean(R.mipmap.six,"球鞋鉴定"));
        stagglist.add(new StaggBean(R.mipmap.one,"球鞋日历"));
        stagglist.add(new StaggBean(R.mipmap.two,"有货推手"));
        stagglist.add(new StaggBean(R.mipmap.three,"0元抽奖"));
        stagglist.add(new StaggBean(R.mipmap.four,"潮物奥莱"));
        stagglist.add(new StaggBean(R.mipmap.five,"潮流趋势"));

        RecyclerViewAdpater recyclerViewAdpater = new RecyclerViewAdpater(stagglist, getContext());
        man_staggview.setAdapter(recyclerViewAdpater);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        man_staggview.setLayoutManager(linearLayoutManager);



        lowstagglist.clear();
        lowstagglist.add(new StaggBean(R.mipmap.s1,"千元潮鞋"));
        lowstagglist.add(new StaggBean(R.mipmap.s2,"AJ精选"));
        lowstagglist.add(new StaggBean(R.mipmap.s3,"空军一号"));
        lowstagglist.add(new StaggBean(R.mipmap.s4,"Yeezy精选"));
        lowstagglist.add(new StaggBean(R.mipmap.f,"更多"));

        RecyclerViewAdpater2 Adpater = new RecyclerViewAdpater2(lowstagglist, getContext());
        man_low_staggview.setAdapter(Adpater);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(getContext());
        LayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        man_low_staggview.setLayoutManager(LayoutManager);



        s=inflate.findViewById(R.id.s);
        bannerlist.clear();
        man_banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });


        lowbannerlist.clear();

        lowbannerlist.add(R.mipmap.a);
        lowbannerlist.add(R.mipmap.b);
        lowbannerlist.add(R.mipmap.c);
        man_low_banner.setImages(lowbannerlist);
        man_low_banner.setDelayTime(2000);
        man_low_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        man_low_banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        man_low_banner.start();

        man_recyclerview.setPullRefreshEnabled(false);
        myTabRecyclerAdpater = new MyTabRecyclerAdpater(goodslist, getContext());
        man_recyclerview.setAdapter(myTabRecyclerAdpater);
        man_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));


        man_recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh(categoryString);
                man_recyclerview.refreshComplete();
                myTabRecyclerAdpater.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                flag = true;
                mPresenter.load(categoryString);
                man_recyclerview.loadMoreComplete();
                myTabRecyclerAdpater.notifyDataSetChanged();
            }
        });

        man_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
              String s = tab.getText().toString();
              if (category.size()>0){
                  for (int i = 0; i < category.size(); i++) {
                      if (category.get(i).getCategory_name().equals(s)){
                          flag = false;
                          categoryString = category.get(i).getCategory_id();
                          mPresenter.categroyGoods(category.get(i).getCategory_id());
                          break;
                      }
                  }
              }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        man_banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("uri","https://www.baidu.com/");
                intent.putExtra("ispa",0);
                startActivity(intent);
            }
        });
        man_low_banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("uri","https://www.baidu.com/");
                intent.putExtra("ispa",0);
                startActivity(intent);
            }
        });





        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.requestAlls();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void resultSuccess(BaseEntity entity, int type) {
        if (type==1){
            BannerEntity bannerEntity = (BannerEntity) entity;
            List<BannerEntity.ValuesBean> values = bannerEntity.getValues();
            for (int i = 0; i < values.size(); i++) {
                bannerlist.add(Api.APP_DOMAIN+values.get(i).getRecommend_url());
            }
            man_banner.setImages(bannerlist);
            man_banner.start();

        }else if (type==2){
            category.clear();
            RecommendEntity recommendEntity = (RecommendEntity) entity;
            List<RecommendEntity.CategoryBean> categorys = recommendEntity.getCategory();
            category.addAll(categorys);
            if (category !=null&& category.size()>0){
                for (int i = 0; i < category.size(); i++) {
                    man_tab.addTab(man_tab.newTab().setText(category.get(i).getCategory_name()));
                }
            }
            categoryString = category.get(0).getCategory_id();

        }else if (type==3){
            goodslist.clear();
            GoodsListEntity goodsListEntity = (GoodsListEntity) entity;
            List<GoodsListEntity.ValuesBean> values = goodsListEntity.getValues();
            if (values!=null){
                goodslist.addAll(values);
            }
            myTabRecyclerAdpater.notifyDataSetChanged();

        }else if (type==4) {
            MenuEntity menuEntity = (MenuEntity) entity;

        }
    }

    @Override
    public void pulltoRefresh(BaseEntity baseEntity) {
        if (flag == false){
            goodslist.clear();
        }

        GoodsListEntity goodsListEntity = (GoodsListEntity) baseEntity;
        List<GoodsListEntity.ValuesBean> values = goodsListEntity.getValues();
        if (values!=null){
            goodslist.addAll(values);
        }


        myTabRecyclerAdpater.notifyDataSetChanged();
    }

    @Override
    public void loadMore(BaseEntity entity) {
        goodslist.clear();
        GoodsListEntity goodsListEntity = (GoodsListEntity) entity;
        List<GoodsListEntity.ValuesBean> values = goodsListEntity.getValues();
        goodslist.addAll(values);

        myTabRecyclerAdpater.notifyDataSetChanged();
    }

    @Override
    public void showMessage(@NonNull String message) {
//        Toast.makeText(getContext() , ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        bannerlist.clear();
    }
}
