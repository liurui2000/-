package com.example.myapplication.mvp.contact;


import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;

public interface HomeContact {
    interface HomeIView extends IView{
        //页面访问成功
        void onSuccess();
        //接口请求成功
        void resultSuccess(BaseEntity entity,int type);
        //下拉刷新
        void pulltoRefresh(BaseEntity baseEntity);
        //上拉加载
        void loadMore(BaseEntity entity);
    }

    interface HomeIModel extends IModel{
        //请求全部接口
        Observable<BaseEntity> requestAll(String catagory, String goodslist);
        //下拉刷新
        Observable<GoodsListEntity> pulltoRefresh(String pramas);
        //上啦加载
        Observable<GoodsListEntity> loadMore(String pramas);
    }
}
