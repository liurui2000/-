package com.example.myapplication.di.module;

import com.example.myapplication.mvp.contact.HomeContact;
import com.example.myapplication.mvp.model.HomeModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {
    private HomeContact.HomeIView iView;

    public HomeModule(HomeContact.HomeIView iView) {
        this.iView = iView;
    }

    @ActivityScope
    @Provides
    HomeContact.HomeIModel providerHomeIModel(HomeModel model){
        return model;
    }

    @ActivityScope
    @Provides
    HomeContact.HomeIView providerHomeIView(){
        return iView;
    }
}
