package com.example.myapplication.mvp.contact;

import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.UploadHeadEntity;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

public interface LreContact {
    interface LreView extends IView{
        void success(BaseEntity entity,int type);
        void error(String error);
        void refreshSuccess(BaseEntity entity);
        void loadSuceess(BaseEntity entity);
    }
    interface LreModel extends IModel{
        Observable<BaseEntity> lreRequest(String params,int type);
        Observable<BaseEntity> lreRefreshRequest(String params,int type);
        Observable<BaseEntity> lreLoadRequest(String params,int type);
        Observable<UploadHeadEntity> uploadRequest(List<MultipartBody.Part> params, int type);
    }

}
