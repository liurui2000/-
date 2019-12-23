package com.example.myapplication.di.module;

import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.LreModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class LreModule {
    private LreContact.LreView lreView;

    public LreModule(LreContact.LreView lreView) {
        this.lreView = lreView;
    }

    @ActivityScope
    @Provides
    LreContact.LreModel providerModel(LreModel model){
        return model;
    }

    @ActivityScope
    @Provides
    LreContact.LreView providerView(){
        return lreView;
    }



}
