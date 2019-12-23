package com.example.myapplication.di.component;

import com.example.myapplication.di.module.HomeModule;
import com.example.myapplication.mvp.ui.fragment.HomeFragment;
import com.example.myapplication.mvp.ui.fragment.child.KidsFragment;
import com.example.myapplication.mvp.ui.fragment.child.LifeFragment;
import com.example.myapplication.mvp.ui.fragment.child.ManFragment;
import com.example.myapplication.mvp.ui.fragment.child.WomanFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = HomeModule.class,dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
    void inject(ManFragment manFragment);
    void inject(WomanFragment womanFragment);
    void inject(KidsFragment kidsFragment);
    void inject(LifeFragment lifeFragment);
}
