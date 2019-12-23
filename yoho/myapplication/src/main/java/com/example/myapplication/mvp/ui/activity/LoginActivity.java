package com.example.myapplication.mvp.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.EncryptUtils;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.LoginEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.utils.RegularUtils;
import com.example.myapplication.view.MyImageView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity<LrePresenter>  implements View.OnClickListener, LreContact.LreView {

    private ImageView iv_back;
    private Button bnt_login;
    private ImageView img_shoui;
    private RelativeLayout relayout_shouji;
    private RelativeLayout relayout_mima;
    private TextView login_text;
    private TextView forgetmima;
    private EditText et_shoujihint;
    private TextView bnt_register;
    private ImageView shoujicuo;
    private EditText et_yanzhengma;
    private EditText et_mima;
    private TextView tv_change;
    public MyImageView image1;
    public MyImageView image2;
    public MyImageView image3;
    public MyImageView image4;
    private ArrayList<Integer> allList = new ArrayList<>();
    private ArrayList<Integer> indexs = new ArrayList<>();
    private ArrayList<Integer> marix = new ArrayList<>();
    private String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJa4C5IKvNRcLWXiLFcF4F+i1S2QAusCMszlQeJV84UetEkczjUdJ4dWbnpRkeAmXCTzRHyO67XKS6GSCuKVO/sf7cyll0i6e+d0MSWB2CTxojYingZSV6ZQO8K1Z3fJyFYSHiRhDwJ4idC80zTyKagsWV29uNa38iQYr4FwbNqZAgMBAAECgYAxV1k6W1eMMg0OsKeRabQVuwoNG3tJEnQtDdSu0zKg3vdohAyh6MR7EvmiA7g86HH8CsPd/y/9WJe/8j6sBO0Ye9gt7eyQ2NiwWvlTuwNmngcSTapVvVI6NEyJFMfQt9PB1EHLNAXlz8jtJUyA7C48jReQD9p/SzAP0VxG7lwyMQJBAOjE7hAZ/6fyP3DB1fG7jr9gONZcz3TUaqx6BUn4GKZnckW08ht9Xqcqft5Hthu8BbLM9ptQ0U8QZekrJwD6ya0CQQClwstZMPu8jLhsgugVwodcG1mPEOiw9Yjnmt9+WTI07Ll2uFv//hRXBnahBBnZbucUYEbUY3kqUX9b3e9TmEodAkEAybPMbxt4VDoxCy6Mi/pxChkBZ4/pHV3sSiU6bAyWn6vIc+sGWRfca5MBePA/N+1IKtY9Y/02QwL8rH5+P/URyQJAL/hdjORGFdzLimuf6pwvPBKWKncEQCHuisghIZmClBpl2duklELddAnkztg2+tvDd/wcw14+NGb9aoKhvhl2aQJAbvcgoPU+xs0CjeexH+TS2S/jKkTRpvP2CpPK/k71m13xWdE8RtMkYY1measRmlIwOfWze7ll/PGT4dxWf31FNg==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void clearImage() {
        marix.clear();
        allList.clear();allList.add(R.mipmap.login_piece_icon1);allList.add(R.mipmap.login_piece_icon2);
        allList.add(R.mipmap.login_piece_icon3);allList.add(R.mipmap.login_piece_icon4);
        allList.add(R.mipmap.login_piece_icon5);allList.add(R.mipmap.login_piece_icon6);
        allList.add(R.mipmap.login_piece_icon7);allList.add(R.mipmap.login_piece_icon8);
        indexs.clear();
        for (;;){
            int index = (int)Math.floor(Math.random() * 8);
            boolean flag = false;
            for (int i1 = 0; i1 < indexs.size(); i1++) {
                if (index==indexs.get(i1)){
                    flag = true;
                    break;
                }
            }
           if (flag==false){
               indexs.add(index);
           }
           if (indexs.size()==4){
               break;
           }
        }

        image1.bitmap = BitmapFactory.decodeResource(getResources(), allList.get(indexs.get(0)));
        image2.bitmap = BitmapFactory.decodeResource(getResources(), allList.get(indexs.get(1)));
        image3.bitmap = BitmapFactory.decodeResource(getResources(), allList.get(indexs.get(2)));
        image4.bitmap = BitmapFactory.decodeResource(getResources(), allList.get(indexs.get(3)));

            int index1 = (int)(Math.floor(Math.random() * 4)+1);
            for (int i = 0;i<index1;i++){
                image1.rotate();
            }
            marix.add(index1);



            int index2 = (int)(Math.floor(Math.random() * 4)+1);
            for (int i = 0;i<index2;i++){
                image2.rotate();
            }
            marix.add(index2);

            int index3 = (int)(Math.floor(Math.random() * 4)+1);
             for (int i = 0;i<index3;i++){
                image3.rotate();
            }
             marix.add(index3);

            int index4 = (int)(Math.floor(Math.random() * 4)+1);
            for (int i = 0;i<index4;i++){
                image4.rotate();
            }
            marix.add(index4);
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        bnt_login = (Button) findViewById(R.id.bnt_login);
        forgetmima = (TextView) findViewById(R.id.forgetmima);
        et_shoujihint = (EditText) findViewById(R.id.et_shoujihint);
        shoujicuo = (ImageView) findViewById(R.id.shoujicuo);


        bnt_login.setOnClickListener(this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("ispage", 0);
                startActivity(intent);
            }
        });

        img_shoui = (ImageView) findViewById(R.id.img_shoui);
        relayout_shouji = (RelativeLayout) findViewById(R.id.relayout_shouji);
        relayout_mima = (RelativeLayout) findViewById(R.id.relayout_mima);
        login_text = (TextView) findViewById(R.id.login_text);


        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = login_text.getText().toString();
                if (s.equals("手机验证码登录")) {
                    img_shoui.setBackgroundResource(R.mipmap.shouji);
                    et_shoujihint.setHint("请输入手机号");
                    pingyi(170f);
                    login_text.setText("账户密码登录");
                    forgetmima.setVisibility(View.GONE);
                    shoujicuo.setVisibility(View.GONE);
                    et_mima.setText("");
                } else {
                    img_shoui.setBackgroundResource(R.mipmap.y_icon_new_1);
                    et_shoujihint.setHint("请输入手机号/邮箱");
                    pingyi(-170f);
                    login_text.setText("手机验证码登录");
                    et_yanzhengma.setText("");
                    forgetmima.setVisibility(View.VISIBLE);
                    shoujicuo.setVisibility(View.VISIBLE);


                }
                clearImage();
            }

        });


        et_yanzhengma = (EditText) findViewById(R.id.et_yanzhengma);
        et_mima = (EditText) findViewById(R.id.et_mima);

        et_shoujihint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String shoujihint = et_shoujihint.getText().toString();
                String mima = et_mima.getText().toString();
                String yanzhengma = et_yanzhengma.getText().toString();
                if (shoujihint.length() > 0) {
                    if (mima.length() > 0 || yanzhengma.length() > 0) {
                        bnt_login.setEnabled(true);
                    } else {
                        bnt_login.setEnabled(false);
                    }
                } else {
                    bnt_login.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_mima.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String shoujihint = et_shoujihint.getText().toString();
                String mima = et_mima.getText().toString();
                String yanzhengma = et_yanzhengma.getText().toString();
                if (shoujihint.length() > 0) {
                    if (mima.length() > 0 || yanzhengma.length() > 0) {
                        bnt_login.setEnabled(true);
                    } else {
                        bnt_login.setEnabled(false);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_yanzhengma.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String shoujihint = et_shoujihint.getText().toString();
                String mima = et_mima.getText().toString();
                String yanzhengma = et_yanzhengma.getText().toString();
                if (shoujihint.length() > 0) {
                    if (mima.length() > 0 || yanzhengma.length() > 0) {
                        bnt_login.setEnabled(true);
                    } else {
                        bnt_login.setEnabled(false);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tv_change = (TextView) findViewById(R.id.tv_change);
        image1 = (MyImageView) findViewById(R.id.image1);
        image2 = (MyImageView) findViewById(R.id.image2);
        image3 = (MyImageView) findViewById(R.id.image3);
        image4 = (MyImageView) findViewById(R.id.image4);
        bnt_register = (TextView) findViewById(R.id.bnt_register);
        bnt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(LoginActivity.this,RegisterActivity.class),1001);
            }
        });
        tv_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearImage();
            }
        });

      clearImage();

      image1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              image1.rotate();
              int type = marix.get(0);
              type=type+1;
                if (type==5){
                    type=1;
                }
              marix.set(0,type);
          }
      });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image2.rotate();
                int type = marix.get(1);
                type=type+1;
                if (type==5){
                    type=1;
                }
                marix.set(1,type);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image3.rotate();
                int type = marix.get(2);
                type=type+1;
                if (type==5){
                    type=1;
                }
                marix.set(2,type);
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image4.rotate();
                int type = marix.get(3);
                type=type+1;
                if (type==5){
                    type=1;
                }
                marix.set(3,type);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bnt_login:
                boolean flag = false;
                for (int i = 0; i < marix.size(); i++) {
                    Log.e("imgae"+i,marix.get(i)+"");
                    if (marix.get(i) != 4) {
                        flag = true;
                    }
                }

                if (flag == false){
//                    Toast.makeText(this, "行了哈哈", Toast.LENGTH_SHORT).show();
                    String shouji = et_shoujihint.getText().toString();
                    String yanzhengma = et_yanzhengma.getText().toString();
                    String mima = et_mima.getText().toString();
//                    if (RegularUtils.getInstance().IsTelNumber(shouji)){
                    if(!yanzhengma.equals("")){
                        login(shouji,yanzhengma);
                    }
                   if (!mima.equals("")){
                       login(shouji,mima);
                   }

//                    }else if (RegularUtils.getInstance().IsEmail(shouji)){
//                        login(shouji,mima);
//
//                    }else {
//                        Toast.makeText(this, "手机号/邮箱不符合规则", Toast.LENGTH_SHORT).show();
//                    }
                }else {
                    Toast.makeText(this, "图片有不是向上的", Toast.LENGTH_SHORT).show();
                }




                break;
        }
    }

    private void login(String shouji, String parmas) {
        byte[] buf = EncryptUtils.encryptRSA(parmas.getBytes(),
                android.util.Base64.decode(privateKey.getBytes(), android.util.Base64.DEFAULT)
                ,false,"RSA/ECB/PKCS1Padding");
        byte[]buff = android.util.Base64.encode(buf, android.util.Base64.DEFAULT);
        String rasPwd = new String(buff);

        JSONObject object=new JSONObject();
        try {
            object.put("username",shouji);
            object.put("password",rasPwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mPresenter.lreAll(object.toString(),ApiDoman.LOGIN_RESULT);


    }


    private void pingyi(float v) {
        if (v > 0) {
            ObjectAnimator translationX = new ObjectAnimator().ofFloat(relayout_mima, "translationX", 0, 0f);
            ObjectAnimator translationY = new ObjectAnimator().ofFloat(relayout_mima, "translationY", -v, 0);
            AnimatorSet animatorSet = new AnimatorSet();  //组合动画
            animatorSet.playTogether(translationX, translationY); //设置动画
            animatorSet.setDuration(800);  //设置动画时间
            animatorSet.start(); //启动
        } else {
            ObjectAnimator translationX = new ObjectAnimator().ofFloat(relayout_mima, "translationX", 0, 0f);
            ObjectAnimator translationY = new ObjectAnimator().ofFloat(relayout_mima, "translationY", 0, v);
            AnimatorSet animatorSet = new AnimatorSet();  //组合动画
            animatorSet.playTogether(translationX, translationY); //设置动画
            animatorSet.setDuration(800);  //设置动画时间
            animatorSet.start(); //启动
        }


    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("ispage", 0);
        startActivity(intent);
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent)
                .lreModule(new LreModule(this))
                .build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.LOGIN_RESULT){
            if (entity instanceof LoginEntity){
                LoginEntity loginEntity = (LoginEntity) entity;
                String msg = loginEntity.getMsg();
                if (msg.equals("请求成功")){
                    String nick_name = loginEntity.getValues().get(0).getUser_name();
                    String user_pwd = loginEntity.getValues().get(0).getUser_pwd();
                    Log.e("###",msg+"---"+nick_name+"---"+user_pwd);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("ispage", 0);
                startActivity(intent);
                SharedPreferences islogin = getSharedPreferences("islogin", MODE_PRIVATE);
                SharedPreferences.Editor edit = islogin.edit();
                edit.putBoolean("islog", true);
                edit.commit();
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
    public void showMessage(@NonNull String message) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001&&resultCode==1002){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("ispage", 0);
            startActivity(intent);
        }
    }
}
