package com.example.myapplication.di.component;


import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.mvp.ui.activity.AddAddressActivity;
import com.example.myapplication.mvp.ui.activity.AddressActivity;
import com.example.myapplication.mvp.ui.activity.ChangeUserActivity;
import com.example.myapplication.mvp.ui.activity.CouponActivity;
import com.example.myapplication.mvp.ui.activity.FootprintActivity;
import com.example.myapplication.mvp.ui.activity.LoginActivity;
import com.example.myapplication.mvp.ui.activity.MainActivity;
import com.example.myapplication.mvp.ui.activity.RegisterActivity;
import com.example.myapplication.mvp.ui.activity.ShoppingCarActivity;
import com.example.myapplication.mvp.ui.activity.ShowDdActivity;
import com.example.myapplication.mvp.ui.activity.WebViewActivity;
import com.example.myapplication.mvp.ui.fragment.ClassifyFragment;
import com.example.myapplication.mvp.ui.fragment.CommunityFragment;
import com.example.myapplication.mvp.ui.fragment.MineFragment;
import com.example.myapplication.mvp.ui.fragment.UfoFragment;
import com.example.myapplication.mvp.ui.fragment.child.Classify_BrandFragment;
import com.example.myapplication.mvp.ui.fragment.child.Classify_CategoryFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = LreModule.class,dependencies = AppComponent.class)
public interface LreComponent {
    void inject(Classify_CategoryFragment classify_categoryFragment);
    void inject(Classify_BrandFragment classify_brandFragment);
    void inject(UfoFragment ufoFragment);
    void inject(CommunityFragment communityFragment);
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    void inject(MainActivity mainActivity);
    void inject(WebViewActivity webViewActivity);
    void inject(ShoppingCarActivity shoppingCarActivity);
    void inject(MineFragment mineFragment);
    void inject(ChangeUserActivity changeUserActivity);
    void inject(ShowDdActivity showDdActivity);
    void inject(AddressActivity showDdActivity);
    void inject(AddAddressActivity showDdActivity);
    void inject(FootprintActivity footprintActivity);
    void inject(CouponActivity couponActivity);
}
