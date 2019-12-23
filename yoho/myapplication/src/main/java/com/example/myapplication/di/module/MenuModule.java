package com.example.myapplication.di.module;

import com.example.myapplication.mvp.contact.MenuContact;
import com.example.myapplication.mvp.model.MenuModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MenuModule {
    private MenuContact.MenuView view;

    public MenuModule(MenuContact.MenuView view) {
        this.view = view;
    }
    @ActivityScope
    @Provides
    MenuContact.MenuView providerMenuView(){
        return view;
    }

    @ActivityScope
    @Provides
    MenuContact.MenuModel providerModel(MenuModel menuModel){
        return menuModel;
    }
}
