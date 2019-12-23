package com.example.myapplication.mvp.ui.fragment.child;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.BannerEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.CategoryGoodsEntity;
import com.example.myapplication.mvp.model.entity.Category_allEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.ui.adapter.MyCategoryAdapter;
import com.example.myapplication.mvp.ui.adapter.OnItemClickListener;
import com.example.myapplication.mvp.ui.adapter.SortGoodsRecyclerAdapter;
import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
import com.jcodecraeer.xrecyclerview.CustomFooterViewCallBack;
import com.jcodecraeer.xrecyclerview.LoadingMoreFooter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 分类页面
 *  子页面
 *  品类
 * */
public class Classify_CategoryFragment extends BaseFragment<LrePresenter> implements LreContact.LreView {

    @BindView(R.id.category_listview)
    public ListView category_listview;

    @BindView(R.id.category_recyclerview)
    public XRecyclerView category_recyclerview;


    private Unbinder bind;
    private ArrayList<String> left = new ArrayList<>();
    private ArrayList<Boolean> left_boolean = new ArrayList<>();
    private MyCategoryAdapter myCategoryAdapter;

    private ArrayList<String> bannerList = new ArrayList<>();
    private ArrayList<CategoryGoodsEntity.ValuesBean> goodsEntities = new ArrayList<>();
    SortGoodsRecyclerAdapter sortGoodsRecyclerAdapter;
    private Banner banner;
    int num = 1;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent).lreModule(new LreModule(this))
                .build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_child_category_layout, null);
        bind = ButterKnife.bind(this, inflate);
        init();
        return inflate;
    }



    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        JSONObject object=new JSONObject();
        try {
            object.put("categoryid","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.CATEGORY_GOODS);

        JSONObject object1=new JSONObject();
        try {
            object1.put("menuid","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object1.toString(), ApiDoman.BRAND_LIST);

        JSONObject object2=new JSONObject();
        try {
            object2.put("menuid","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object1.toString(), ApiDoman.CATEGORY_ALL);
        mPresenter.lreAll("",ApiDoman.BANNER);

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
        if (type == ApiDoman.CATEGORY_GOODS){
            if (entity instanceof CategoryGoodsEntity){
                CategoryGoodsEntity categoryGoodsEntity = (CategoryGoodsEntity) entity;
                Log.e("categoryGoodsEntity",categoryGoodsEntity.toString());
                if (categoryGoodsEntity!=null){
                    goodsEntities.clear();
                    List<CategoryGoodsEntity.ValuesBean> values = categoryGoodsEntity.getValues();
                    goodsEntities.addAll(values);
                }
                    sortGoodsRecyclerAdapter.notifyDataSetChanged();

            }

        }else if (type == ApiDoman.BRAND_LIST){
            if (entity instanceof BrandListEntity){
                BrandListEntity brandListEntity = (BrandListEntity) entity;
                Log.e("brandListEntity",brandListEntity.toString());

            }

        }else if (type == ApiDoman.CATEGORY_ALL){
            if (entity instanceof Category_allEntity){
                Category_allEntity category_allEntity = (Category_allEntity) entity;
                Log.e("category_allEntity",category_allEntity.toString());
                if (category_allEntity !=null){
                    left.clear();
                    left_boolean.clear();
                    for (int i = 0; i < category_allEntity.getValues().size(); i++) {
                        left.add(category_allEntity.getValues().get(i).getName());
                        if (i==0){
                            left_boolean.add(true);
                        }else {
                            left_boolean.add(false);
                        }
                    }
                    myCategoryAdapter.notifyDataSetChanged();
                }
            }
        }else if (type == ApiDoman.BANNER){
            if (entity instanceof BannerEntity){
                BannerEntity bannerEntity = (BannerEntity) entity;

                bannerList.clear();
                if (bannerEntity!=null){
                    for (int i = 0; i < bannerEntity.getValues().size(); i++) {
                        bannerList.add(Api.APP_DOMAIN+bannerEntity.getValues().get(i).getRecommend_url());
                    }
                }
                banner.setImages(bannerList);
                banner.start();

            }
        }
    }

    @Override
    public void error(String error) {
        if (error!=null){
            Log.e("error",error);
        }
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
        myCategoryAdapter = new MyCategoryAdapter(getContext(), left, left_boolean);
        category_listview.setAdapter(myCategoryAdapter);
        myCategoryAdapter.notifyDataSetChanged();
        myCategoryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onclick(int i, TextView textView, ArrayList<Boolean> booleans) {
                textView.setTextSize(18);

                for (int j = 0; j < booleans.size(); j++) {
                    if (j!=i){
                        booleans.set(j,false);
                    }else {
                        booleans.set(i,true);
                    }
                }
                num = i+1;


                category_recyclerview.scrollToPosition(0);
                category_recyclerview.refresh();

                myCategoryAdapter.notifyDataSetChanged();
            }
        });

        sortGoodsRecyclerAdapter = new SortGoodsRecyclerAdapter(goodsEntities,getContext());
        category_recyclerview.setAdapter(sortGoodsRecyclerAdapter);
        category_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),3));
        category_recyclerview.setRefreshProgressStyle(ProgressStyle.Pacman);
        category_recyclerview.setRefreshHeader(new ArrowRefreshHeader(getContext()));

        View head = getLayoutInflater().inflate(R.layout.sort_xrecyclerview_head, null);
        banner = head.findViewById(R.id.iv_head_banner);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setDelayTime(3000);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        category_recyclerview.addHeaderView(head);

        View foot = getLayoutInflater().inflate(R.layout.sort_xrecyclerview_foot, null);
        category_recyclerview.setFootView(foot, new CustomFooterViewCallBack() {
            @Override
            public void onLoadingMore(View yourFooterView) {
                Log.e("###","onLoadingMore");
            }

            @Override
            public void onLoadMoreComplete(View yourFooterView) {
                Log.e("###","onLoadMoreComplete");
            }

            @Override
            public void onSetNoMore(View yourFooterView, boolean noMore) {
                Log.e("###","onSetNoMore");
            }
        });


        category_recyclerview.setPullRefreshEnabled(true);
        category_recyclerview.setLoadingMoreEnabled(false);
        category_recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                JSONObject sortgoods = new JSONObject();
                try {
                    sortgoods.put("categoryid",num+"");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mPresenter.lreAll("",ApiDoman.BANNER);
                mPresenter.lreAll(sortgoods.toString(),ApiDoman.CATEGORY_GOODS);
                category_recyclerview.refreshComplete();

            }

            @Override
            public void onLoadMore() {

            }
        });
    }

}
