package com.example.myapplication.mvp.presenter;

import com.example.myapplication.mvp.contact.MenuContact;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MenuPresenter extends BasePresenter<MenuContact.MenuModel,MenuContact.MenuView> {

    @Inject
    public MenuPresenter(MenuContact.MenuModel model, MenuContact.MenuView rootView) {
        super(model, rootView);
    }

    public void requestMenu(){
        mModel.requestMenu().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MenuEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MenuEntity entity) {
                        if (mRootView!=null){
                            mRootView.ok(entity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
