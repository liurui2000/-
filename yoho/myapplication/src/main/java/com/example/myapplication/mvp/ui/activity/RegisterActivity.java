package com.example.myapplication.mvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.LoginEntity;
import com.example.myapplication.mvp.model.entity.RegisterEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity<LrePresenter> implements LreContact.LreView {
    @BindView(R.id.register_back)
    TextView registerBack;
    @BindView(R.id.register_close)
    TextView registerClose;
    @BindView(R.id.register_kouling_edit)
    EditText registerKoulingEdit;
    @BindView(R.id.register_yanzheng_edit)
    EditText registerYanzhengEdit;

    @BindView(R.id.register_getzcode)
    TextView registerGetzcode;

    @BindView(R.id.register_pwd_edit)
    EditText registerPwdEdit;

    @BindView(R.id.login_change_seepwd)
    CheckBox loginChangeSeepwd;
    @BindView(R.id.register_kouling)
    EditText registerKouling;
    @BindView(R.id.register_change_onepieces)
    TextView registerChangeOnepieces;
    @BindView(R.id.register_img1)
    ImageView registerImg1;
    @BindView(R.id.register_img2)
    ImageView registerImg2;
    @BindView(R.id.register_img3)
    ImageView registerImg3;
    @BindView(R.id.register_img4)
    ImageView registerImg4;
    @BindView(R.id.register_doit)
    Button registerDoit;
    private int[] pics = new int[8];

    private int[] isTops = new int[4];
    private int[] nowPics = new int[4];
    private String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJa4C5IKvNRcLWXiLFcF4F+i1S2QAusCMszlQeJV84UetEkczjUdJ4dWbnpRkeAmXCTzRHyO67XKS6GSCuKVO/sf7cyll0i6e+d0MSWB2CTxojYingZSV6ZQO8K1Z3fJyFYSHiRhDwJ4idC80zTyKagsWV29uNa38iQYr4FwbNqZAgMBAAECgYAxV1k6W1eMMg0OsKeRabQVuwoNG3tJEnQtDdSu0zKg3vdohAyh6MR7EvmiA7g86HH8CsPd/y/9WJe/8j6sBO0Ye9gt7eyQ2NiwWvlTuwNmngcSTapVvVI6NEyJFMfQt9PB1EHLNAXlz8jtJUyA7C48jReQD9p/SzAP0VxG7lwyMQJBAOjE7hAZ/6fyP3DB1fG7jr9gONZcz3TUaqx6BUn4GKZnckW08ht9Xqcqft5Hthu8BbLM9ptQ0U8QZekrJwD6ya0CQQClwstZMPu8jLhsgugVwodcG1mPEOiw9Yjnmt9+WTI07Ll2uFv//hRXBnahBBnZbucUYEbUY3kqUX9b3e9TmEodAkEAybPMbxt4VDoxCy6Mi/pxChkBZ4/pHV3sSiU6bAyWn6vIc+sGWRfca5MBePA/N+1IKtY9Y/02QwL8rH5+P/URyQJAL/hdjORGFdzLimuf6pwvPBKWKncEQCHuisghIZmClBpl2duklELddAnkztg2+tvDd/wcw14+NGb9aoKhvhl2aQJAbvcgoPU+xs0CjeexH+TS2S/jKkTRpvP2CpPK/k71m13xWdE8RtMkYY1measRmlIwOfWze7ll/PGT4dxWf31FNg==";


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent)
                .lreModule(new LreModule(this))
                .build().inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_register;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initPics();
        initBack();
        initIMG();
    }

    /**
     * 作用:初始化图片
     */
    private void initPics() {
        pics[0] = R.mipmap.login_piece_icon1;
        pics[1] = R.mipmap.login_piece_icon2;
        pics[2] = R.mipmap.login_piece_icon3;
        pics[3] = R.mipmap.login_piece_icon4;
        pics[4] = R.mipmap.login_piece_icon5;
        pics[5] = R.mipmap.login_piece_icon6;
        pics[6] = R.mipmap.login_piece_icon7;
        pics[7] = R.mipmap.login_piece_icon8;

    }
    /**
     * 作用:设置图片动画
     */
    private void initIMG() {
        randomPickimg();
        setImageOnClick();

    }


    private void setImageOnClick() {
        registerImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( isTops[0] == 3){
                    isTops[0] = 0;
                }else {
                    isTops[0]= isTops[0]+1;
                }
                ObjectAnimator rotate = ObjectAnimator.ofFloat(registerImg1, "rotation", 0f, isTops[0]*90f);
                rotate.setDuration(0);//时间
                rotate.start();//开始

                Log.i("###img", "onClick: image1"+isTops[0]);
            }
        });
        registerImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( isTops[1] == 3){
                    isTops[1] = 0;
                }else {
                    isTops[1]= isTops[1]+1;
                }
                ObjectAnimator rotate = ObjectAnimator.ofFloat(registerImg2, "rotation", 0f, isTops[1]*90f);
                rotate.setDuration(0);//时间
                rotate.start();//开始

                Log.i("###img", "onClick: image2"+isTops[1]);
            }
        });
        registerImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( isTops[2] == 3){
                    isTops[2] = 0;
                }else {
                    isTops[2]= isTops[2]+1;
                }
                ObjectAnimator rotate = ObjectAnimator.ofFloat(registerImg3, "rotation", 0f, isTops[2]*90f);
                rotate.setDuration(0);//时间
                rotate.start();//开始

                Log.i("###img", "onClick: image3"+isTops[2]);
            }
        });
        registerImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( isTops[3] == 3){
                    isTops[3] = 0;
                }else {
                    isTops[3]= isTops[3]+1;
                }
                ObjectAnimator rotate = ObjectAnimator.ofFloat(registerImg4, "rotation", 0f, isTops[3]*90f);
                rotate.setDuration(0);//时间
                rotate.start();//开始

                Log.i("###img", "onClick: image4"+isTops[3]);
            }
        });
    }
    private void randomPickimg() {
        int i = 0;
        while (i<4){
            int x = (int )( Math.random()*8);

            for (int j = 0; j < nowPics.length; j++) {
                if(nowPics[j] ==  pics[x]){
                    continue;
                }
            }
            nowPics[i] = pics[x];
            i++;
        }

        for (int x = 0; x < isTops.length; x++) {
            int ram = (int )( Math.random()*3);
            isTops[x] = ram;
        }

        makePicTurn(registerImg1,0);
        makePicTurn(registerImg2,1);
        makePicTurn(registerImg3,2);
        makePicTurn(registerImg4,3);
    }

    private void makePicTurn(ImageView imageView,int position) {
        imageView.setBackgroundResource(nowPics[position]);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(imageView, "rotation", 0f, isTops[position]*90f);
        rotate.setDuration(1);//时间
        rotate.start();//开始

    }

    //    设置返回
    private void initBack() {
        registerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(1001,new Intent());
                finish();
            }
        });

        registerClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setResult(1002,new Intent());
                finish();
            }
        });

        registerChangeOnepieces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initIMG();
            }
        });

        registerDoit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isTops = getIsTops();
                if (isTops == true){
                    if (!registerKoulingEdit.getText().toString().equals("")&&!registerPwdEdit.equals("")){
                        String Kouling = registerKoulingEdit.getText().toString();
                        String PwdEdit = registerPwdEdit.getText().toString();
                        register(Kouling,PwdEdit);


                    }else {
                        Toast.makeText(RegisterActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "图片要都是在向上的", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void register(String kouling, String pwdEdit) {
        byte[] buf = EncryptUtils.encryptRSA(pwdEdit.getBytes(),
                android.util.Base64.decode(privateKey.getBytes(), android.util.Base64.DEFAULT)
                ,false,"RSA/ECB/PKCS1Padding");
        byte[]buff = android.util.Base64.encode(buf, android.util.Base64.DEFAULT);
        String rasPwd = new String(buff);
        JSONObject object=new JSONObject();
        try {
            object.put("username",kouling);
            object.put("password",rasPwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mPresenter.lreAll(object.toString(), ApiDoman.REGISTER_RESULT);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(1001);
    }
    public boolean getIsTops() {
        for (int i = 0; i < isTops.length; i++) {
            if(isTops[i] !=0){
                return false;
            }
        }
        return true;
    }

    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.REGISTER_RESULT){
            if (entity instanceof RegisterEntity){
                RegisterEntity registerEntity = (RegisterEntity) entity;
                String msg = registerEntity.getMsg();
                if (msg.equals("请求成功")){
                    String nick_name = registerEntity.getValues().get(0).getUser_name();
                    String user_pwd = registerEntity.getValues().get(0).getUser_pwd();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.putExtra("ispage",4);
                    startActivity(intent);
                    Log.e("###",msg+"---"+nick_name+"---"+user_pwd);
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
}
