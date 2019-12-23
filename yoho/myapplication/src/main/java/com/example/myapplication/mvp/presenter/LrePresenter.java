package com.example.myapplication.mvp.presenter;

import android.util.Log;

import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

@ActivityScope
public class LrePresenter extends BasePresenter<LreContact.LreModel,LreContact.LreView> {
    @Inject
    public LrePresenter(LreContact.LreModel model, LreContact.LreView rootView) {
        super(model, rootView);
    }
    private int page = 1;
    public void lreAll(String parmas,int type){

        mModel.lreRequest(parmas,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity entity) {
                        if (mRootView!=null){
                            mRootView.success(entity,type);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mRootView!=null){
                            mRootView.error(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void lreRefresh(String params,int type){
        page=1;
        String pars = "";
        try {
            JSONObject object=new JSONObject(params);
            object.put("page",""+page);
            pars=object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mModel.lreRefreshRequest(pars,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity entity) {
                        if (mRootView!=null){
                            mRootView.success(entity,type);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mRootView!=null){
                            mRootView.error(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
    public void lreloadmore(String params,int type){
        page++;
        String pars = "";
        try {
            JSONObject object=new JSONObject(params);
            object.put("page",""+page);
            pars=object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mModel.lreLoadRequest(pars,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity entity) {
                        if (mRootView!=null){
                            mRootView.success(entity,type);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mRootView!=null){
                            mRootView.error(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void uploadHead(List<MultipartBody.Part> params, int type){
        mModel.uploadRequest(params,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseEntity entity) {
                        if (mRootView!=null){
                            mRootView.success(entity,type);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mRootView!=null){
                            mRootView.error(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
