package com.example.myapplication.mvp.contact;

import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;

public interface MenuContact {
    interface MenuView extends IView{
        void ok(MenuEntity menuEntity);
    }
    interface MenuModel extends IModel{
        Observable<MenuEntity> requestMenu();
    }
}
