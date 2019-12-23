package com.example.myapplication.mvp.model;

import com.example.myapplication.mvp.contact.MenuContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class MenuModel extends BaseModel implements MenuContact.MenuModel {
    @Inject
    public MenuModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<MenuEntity> requestMenu() {
        return mRepositoryManager.obtainRetrofitService(Api.class).getMenuList();
    }
}
