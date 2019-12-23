package com.example.myapplication.mvp.ui.fragment.child;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LifeFragment extends BaseFragment<HomePresenter> implements HomeContact.HomeIView {

    @BindView(R.id.life_banner)
    public Banner life_banner;
    @BindView(R.id.lifetop_staggview)
    public RecyclerView lifetop_staggview;
    @BindView(R.id.lifelow_staggview)
    public RecyclerView lifelow_staggview;
    @BindView(R.id.life_tab)
    public TabLayout life_tab;
    @BindView(R.id.life_recyclerview)
    public XRecyclerView life_recyclerview;
    private Unbinder bind;
    @BindView(R.id.life_appbarlayout)
    public AppBarLayout life_appbarlayout;

    public  ArrayList<String> bannerlist = new ArrayList<>();
    public  ArrayList<StaggBean> topstagglist = new ArrayList<>();
    private ArrayList<StaggBean> lowstagglist = new ArrayList<>();
    private ArrayList<RecommendEntity.CategoryBean> category = new ArrayList<>();
    private ArrayList<GoodsListEntity.ValuesBean> goodslist = new ArrayList<>();
    private MyTabRecyclerAdpater myTabRecyclerAdpater;
    private String categoryString = "";
    private boolean flag = false;
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
        View inflate = inflater.inflate(R.layout.fragment_child_life_layout, null);
        bind = ButterKnife.bind(this, inflate);
        life_tab.setSelectedTabIndicatorColor(Color.BLACK);
        topstagglist.clear();

        topstagglist.add(new StaggBean(R.mipmap.recy1,"￥188"));
        topstagglist.add(new StaggBean(R.mipmap.recy2,"￥59"));
        topstagglist.add(new StaggBean(R.mipmap.recy3,"￥99"));
        topstagglist.add(new StaggBean(R.mipmap.recy4,"￥1698"));
        topstagglist.add(new StaggBean(R.mipmap.recy5,"￥298"));
        topstagglist.add(new StaggBean(R.mipmap.recy6,"￥2500"));

        RecyclerViewAdpater recyclerViewAdpater = new RecyclerViewAdpater(topstagglist, getContext());
        lifetop_staggview.setAdapter(recyclerViewAdpater);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        lifetop_staggview.setLayoutManager(linearLayoutManager);


        life_appbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                int[] arr = new int[2];
                life_tab.getLocationOnScreen(arr);
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


        lowstagglist.clear();
        lowstagglist.add(new StaggBean(R.mipmap.recy1,"￥188"));
        lowstagglist.add(new StaggBean(R.mipmap.recy2,"￥59"));
        lowstagglist.add(new StaggBean(R.mipmap.recy3,"￥99"));
        lowstagglist.add(new StaggBean(R.mipmap.recy4,"￥1698"));
        lowstagglist.add(new StaggBean(R.mipmap.recy5,"￥298"));
        lowstagglist.add(new StaggBean(R.mipmap.recy6,"￥2500"));

        RecyclerViewAdpater2 Adpater = new RecyclerViewAdpater2(lowstagglist, getContext());
        lifelow_staggview.setAdapter(Adpater);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(getContext());
        LayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        lifelow_staggview.setLayoutManager(LayoutManager);



        bannerlist.clear();
        life_banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });



        life_recyclerview.setPullRefreshEnabled(false);
        myTabRecyclerAdpater = new MyTabRecyclerAdpater(goodslist, getContext());
        life_recyclerview.setAdapter(myTabRecyclerAdpater);
        life_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));


        life_recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh(categoryString);
                life_recyclerview.refreshComplete();
                myTabRecyclerAdpater.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                flag = true;
                mPresenter.load(categoryString);
                life_recyclerview.loadMoreComplete();
                myTabRecyclerAdpater.notifyDataSetChanged();
            }
        });

        life_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

        life_banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
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
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
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
            if (life_banner!=null){
                life_banner.setImages(bannerlist);
                life_banner.start();
            }
        }else if (type==2){
            category.clear();
            RecommendEntity recommendEntity = (RecommendEntity) entity;
            List<RecommendEntity.CategoryBean> categorys = recommendEntity.getCategory();
            category.addAll(categorys);
            if (category !=null&& category.size()>0&&life_tab!=null){
                for (int i = 0; i < category.size(); i++) {
                    life_tab.addTab(life_tab.newTab().setText(category.get(i).getCategory_name()));
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

    }
}
