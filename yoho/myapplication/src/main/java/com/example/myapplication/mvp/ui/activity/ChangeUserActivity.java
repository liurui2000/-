package com.example.myapplication.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.ChangeUserEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeUserActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {


    @BindView(R.id.user_back_txt)
    TextView userBackTxt;
    @BindView(R.id.user_name_txt)
    EditText userNameTxt;
    @BindView(R.id.nick_name_txt)
    EditText nickNameTxt;
    @BindView(R.id.user_sex_txt)
    EditText user_sex_txt;
    @BindView(R.id.user_birthady_txt)
    EditText userBirthadyTxt;
    @BindView(R.id.user_hei_txt)
    EditText userHeiTxt;
    @BindView(R.id.bnt_close)
    Button bntClose;

    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.CHANGE_USER){
            if (entity instanceof ChangeUserEntity){
                ChangeUserEntity changeUserEntity = (ChangeUserEntity) entity;
                String msg = changeUserEntity.getMsg();
                if (msg.equals("修改成功")){
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void refreshSuccess(BaseEntity entity) {

    }

    @Override
    public void loadSuceess(BaseEntity entity) {

    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().lreModule(new LreModule(this))
                .appComponent(appComponent)
                .build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_change_user;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        userBackTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bntClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //userNameTxt,nickNameTxt,userSexTxtuserBirthadyTxt;userHeiTxt;
                String userName = userNameTxt.getText().toString();
                String nickName = nickNameTxt.getText().toString();
                String userBirthady = userBirthadyTxt.getText().toString();
                String user_sex = user_sex_txt.getText().toString();
                String userHei = userHeiTxt.getText().toString();
                if (!userName.equals("")&&!nickName.equals("")&&!user_sex.equals("")&&!userHei.equals("")&&
                !userBirthady.equals("")){
                    JSONObject object=new JSONObject();
                    try {
                        object.put("userid","1");
                        object.put("nickname",nickName);
                        object.put("usersex",user_sex);
                        object.put("userbirthday",userBirthady);
                        object.put("userheight",userHei);
                        object.put("userweight","88kg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mPresenter.lreAll(object.toString(), ApiDoman.CHANGE_USER);

                }
            }
        });
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
