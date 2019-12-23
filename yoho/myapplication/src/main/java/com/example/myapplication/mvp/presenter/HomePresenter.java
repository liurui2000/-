package com.example.myapplication.mvp.presenter;

import android.util.Log;

import com.example.myapplication.mvp.contact.HomeContact;
import com.example.myapplication.mvp.model.entity.BannerEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.example.myapplication.mvp.model.entity.RecommendEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class HomePresenter extends BasePresenter<HomeContact.HomeIModel,HomeContact.HomeIView> {
    @Inject
    public HomePresenter(HomeContact.HomeIModel model, HomeContact.HomeIView rootView) {
        super(model, rootView);
    }
    private int page=1;//页数
    private String categoryId="1";//分类id
    private String menuId="1";//菜单 ID
    public void requestAlls(){
        String categorySrc="{\"menu\":\""+menuId+"\"}";
        JSONObject object = new JSONObject();

        try {
            object.put("category",categoryId);
            object.put("page",""+page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String goodsListSrc = object.toString();
        mModel.requestAll(categorySrc,goodsListSrc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity entity) {
                        if (mRootView!=null){
                            if (entity instanceof BannerEntity){
                                mRootView.resultSuccess(entity,1);
                            }else if (entity instanceof RecommendEntity){
                                mRootView.resultSuccess(entity,2);
                            }else if (entity instanceof GoodsListEntity){
                                mRootView.resultSuccess(entity,3);
                            }else if (entity instanceof MenuEntity){
                                mRootView.resultSuccess(entity,4);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (mRootView!=null){
                            mRootView.onSuccess();
                        }
                    }
                });

    }
    //分类筛选 刷新
    public void categroyGoods(String categoryId){
        page=1;
        JSONObject object=new JSONObject();
        try {
            object.put("category",categoryId);
            object.put("page",""+page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String goodsListSrc=object.toString();
        mModel.pulltoRefresh(goodsListSrc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GoodsListEntity>() {
            @Override
            public void accept(GoodsListEntity goodsListEntity) throws Exception {
                if (mRootView!=null){
                    mRootView.pulltoRefresh(goodsListEntity);
                }
            }
        });
    }

    //下啦刷新
    public void refresh(String categoryId){
        page=1;
        JSONObject object=new JSONObject();
        try {
            object.put("category",categoryId);
            object.put("page",""+page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String goodsListSrc=object.toString();
        mModel.pulltoRefresh(goodsListSrc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GoodsListEntity>() {
            @Override
            public void accept(GoodsListEntity goodsListEntity) throws Exception {
                if (goodsListEntity==null||goodsListEntity.getValues()==null){
                    mRootView.showMessage("无数据");
                }else
                    mRootView.pulltoRefresh(goodsListEntity);
            }
        });
    }

    //上啦加载
    public void load(String categoryId){
        page=page+1;
        JSONObject object=new JSONObject();
        try {
            object.put("category",categoryId);
            object.put("page",""+page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String goodsListSrc=object.toString();
        mModel.loadMore(goodsListSrc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodsListEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GoodsListEntity goodsListEntity) {
                if (mRootView!=null){
                    if (goodsListEntity==null||goodsListEntity.getValues()==null){
                        mRootView.showMessage("无数据");
                    }else
                        mRootView.pulltoRefresh(goodsListEntity);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mRootView!=null){
                    mRootView.showMessage("最后一页");
                }

            }

            @Override
            public void onComplete() {

            }
        });
    }


}
