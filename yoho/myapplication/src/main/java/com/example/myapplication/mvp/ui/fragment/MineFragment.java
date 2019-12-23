package com.example.myapplication.mvp.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.di.component.DaggerLreComponent;
import com.example.myapplication.di.module.LreModule;
import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.QueryUserEntity;
import com.example.myapplication.mvp.model.entity.UploadHeadEntity;
import com.example.myapplication.mvp.presenter.LrePresenter;
import com.example.myapplication.mvp.ui.activity.MainActivity;
import com.example.myapplication.mvp.ui.activity.MessageActivity;
import com.example.myapplication.mvp.ui.activity.SetActivity;
import com.example.myapplication.mvp.ui.activity.ZXingActivity;
import com.example.myapplication.mvp.ui.fragment.child.MineChildMarFragment;
import com.example.myapplication.mvp.ui.fragment.child.MineChildSorFragment;
import com.example.myapplication.utils.AnimationUtils;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.wyp.avatarstudio.AvatarStudio;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MineFragment extends BaseFragment<LrePresenter> implements LreContact.LreView {
    @BindView(R.id.mine_share)
    ImageView mineShare;
    @BindView(R.id.mine_mess)
    ImageView mineMess;
    @BindView(R.id.mine_zxing)
    ImageView mineZxing;
    @BindView(R.id.mine_title_bg)
    RelativeLayout mineTitleBg;
    @BindView(R.id.mine_right_setting)
    ImageView mineRightSetting;
    @BindView(R.id.mine_left_setting)
    ImageView mineLeftSetting;
    @BindView(R.id.mine_title_head)
    ImageView mineTitleHead;
    @BindView(R.id.mine_grow)
    TextView mineGrow;
    @BindView(R.id.mine_default_header)
    ImageView mineDefaultHeader;
    @BindView(R.id.mine_name)
    TextView mineName;
    @BindView(R.id.mine_qianming)
    TextView mineQianming;
    @BindView(R.id.mine_sor)
    RadioButton mineSor;
    @BindView(R.id.mine_market)
    RadioButton mineMarket;
    @BindView(R.id.mine_viewpager)
    ViewPager mineViewpager;
    @BindView(R.id.mine_scroll)
    ScrollView mineScroll;

    private AnimationUtils animationUtils;
//    @BindView(R.id.mine_exit_login)
//    Button mineExitLogin;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLreComponent.builder().appComponent(appComponent)
                .lreModule(new LreModule(this))
                .build().inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_mine_layout, null);
        return inflate;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {



        initFragment();
        initMyView();
        initAnmi();
        initHead();
    }



    /**
    * 作用:初始化动画效果
    */
    @SuppressLint("ClickableViewAccessibility")
    private void initAnmi() {
        mineScroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int eventAction = motionEvent.getAction();
                int scrollY = mineScroll.getScrollY();
                switch (eventAction) {
                    case MotionEvent.ACTION_MOVE:
                        if(scrollY<=500){
                            animationUtils.showAndHiddenAnimation(mineTitleHead,1,0);
                            animationUtils.showAndHiddenAnimation(mineRightSetting,1,0);
                            float scrollY1 = scrollY/5;
                            animationUtils.showAndHiddenAnimation(mineLeftSetting,1f,1f-scrollY1/100);
                        }else if(scrollY>500){
                            animationUtils.showAndHiddenAnimation(mineLeftSetting,1f,0);
                            float scrollY1 = (scrollY-500)/5;
                            animationUtils.showAndHiddenAnimation(mineTitleHead,0,scrollY1/100);
                            animationUtils.showAndHiddenAnimation(mineRightSetting,0,scrollY1/100);
                        }

//                        if(scrollY<=1000){
                            float scrollY1 = scrollY/5;
                            animationUtils.showAndHiddenAnimation(mineGrow,0,scrollY1/100);
                            animationUtils.showAndHiddenAnimation(mineTitleBg,0,scrollY1/100);

//                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(scrollY<=500){
                            animationUtils.showAndHiddenAnimation(mineTitleHead,1,0);
                            animationUtils.showAndHiddenAnimation(mineRightSetting,1,0);
                            float scrollY2 = scrollY/5;
                            animationUtils.showAndHiddenAnimation(mineLeftSetting,1f,1f-scrollY2/100);
                        }else if(scrollY>500){
                            animationUtils.showAndHiddenAnimation(mineLeftSetting,1f,0);
                            float scrollY2 = (scrollY-500)/5;
                            animationUtils.showAndHiddenAnimation(mineTitleHead,0,scrollY2/100);
                            animationUtils.showAndHiddenAnimation(mineRightSetting,0,scrollY2/100);
                        }
//                        if(scrollY<=1000){
                        float scrollY2 = scrollY/5;
                        animationUtils.showAndHiddenAnimation(mineGrow,0,scrollY2/100);
                        animationUtils.showAndHiddenAnimation(mineTitleBg,0,scrollY2/100);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
    /**
    * 作用:初始化viewpager
    */
    private void initFragment() {
        Fragment[] fragments = {new MineChildSorFragment(), new MineChildMarFragment()};
        mineViewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });
        mineSor.setChecked(true);
        mineViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mineSor.setChecked(true);
                } else {
                    mineMarket.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mineSor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mineViewpager.setCurrentItem(0);
                }
            }
        });
        mineMarket.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mineViewpager.setCurrentItem(1);
                }
            }
        });

        mineMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MessageActivity.class));
            }
        });
    }

    //初始化页面信息
    private void initMyView() {
        animationUtils = new AnimationUtils();
        Glide.with(getActivity()).load(R.drawable.photos).apply(RequestOptions.circleCropTransform()).into(mineTitleHead);
        Glide.with(getActivity()).load(R.drawable.k).apply(RequestOptions.circleCropTransform()).into(mineDefaultHeader);
        mineSor.setChecked(true);
        animationUtils.showAndHiddenAnimation(mineTitleBg, 1, 0);
        animationUtils.showAndHiddenAnimation(mineTitleHead, 1, 0);
        animationUtils.showAndHiddenAnimation(mineGrow, 1, 0);
        animationUtils.showAndHiddenAnimation(mineRightSetting, 1, 0);

        mineShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });


            mineName.setText("LR");


        mineZxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ZXingActivity.class));
            }
        });

        mineLeftSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SetActivity.class));

            }
        });

        mineDefaultHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent();
//                intent.setAction(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent,202);
                new AvatarStudio.Builder(getActivity())
                        .setTextColor(Color.BLUE)
                        .setAspect(1, 1)//裁剪比例 默认1：1
                        .setOutput(200, 200)//裁剪大小 默认200*200
                        .setText("打开相机", "从相册中选取", "取消")
                        .show(new AvatarStudio.CallBack() {
                            @Override
                            public void callback(String uri) {
                                //uri为图片路径
                                Glide.with(getActivity()).load(uri).apply(new RequestOptions().circleCrop()).into(mineDefaultHeader);

                                File file = new File(uri);
                                MultipartBody.Builder builder = new MultipartBody.Builder();
                                builder.setType(MultipartBody.FORM);
                                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file );
                                builder.addFormDataPart("uploadedfile",System.currentTimeMillis()+".jpg",requestBody);
                                builder.addFormDataPart("userid","1");
                                mPresenter.uploadHead(builder.build().parts(),ApiDoman.UPLOAD_HEAD);
                                Log.e("###", builder.build().parts().toString() );
                            }
                        });


            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        animationUtils = null;
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("分享至");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，确保SDcard下面存在此张图片
        oks.setImagePath("/sdcard/test.jpg");
        // url在微信、Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(getActivity());
    }

    @Override
    public void success(BaseEntity entity, int type) {
        if (type == ApiDoman.QUERY_USER){
            if (entity instanceof QueryUserEntity){
                QueryUserEntity queryUserEntity = (QueryUserEntity) entity;
                List<QueryUserEntity.ValuesBean> values = queryUserEntity.getValues();
                String qr_code = values.get(0).getUser_head();
                boolean b = qr_code.startsWith("/");
                if (b){
                    Glide.with(getContext()).load(Api.APP_DOMAIN+qr_code).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mineDefaultHeader);
                }else {
                    Glide.with(getContext()).load(qr_code).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mineDefaultHeader);
                }

            }
        }else if (type == ApiDoman.UPLOAD_HEAD){
            if (entity instanceof UploadHeadEntity){
                UploadHeadEntity uploadHeadEntity = (UploadHeadEntity) entity;
                String msg = uploadHeadEntity.getMsg();
                Log.e("###",msg);
                if (msg.equals("修改成功")){
                    Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
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


    private void initHead() {

        JSONObject object=new JSONObject();
        try {
            object.put("userid","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresenter.lreAll(object.toString(), ApiDoman.QUERY_USER);


    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 202){
//            if (resultCode == Activity.RESULT_OK){
//                Uri s = data.getData();
//                Glide.with(getContext()).load(s).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mineDefaultHeader);
//            }
//        }
//    }
}
