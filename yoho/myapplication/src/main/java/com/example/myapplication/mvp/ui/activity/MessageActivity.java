package com.example.myapplication.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.mvp.model.entity.MessageEntity;
import com.example.myapplication.mvp.ui.adapter.RecyclerMessage_Adpater;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MessageActivity extends BaseActivity {

    @BindView(R.id.message_back)
    TextView messageBack;
    @BindView(R.id.message_recycler)
    XRecyclerView messageRecycler;
    private Unbinder bind;
    private ArrayList<MessageEntity> list = new ArrayList<>();
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_message;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        messageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        list.clear();
        list.add(new MessageEntity(R.drawable.message1,"活动推荐","2019-12-16 21:33:14","Levi's Dickies 3折起,东著满额立减600，万款减的更多哦,快来哦..."));
        list.add(new MessageEntity(R.drawable.message2,"会员信息","","会员等级变更提醒,会员生日福利"));
        list.add(new MessageEntity(R.drawable.message3,"我的资产","2019-11-29 10:49:53","欢迎您加入YOHO!FAMILY!159.00元新人专用券..."));
        list.add(new MessageEntity(R.drawable.message4,"服务通知","","没有收到任何关于你的消息"));
        list.add(new MessageEntity(R.drawable.message5,"其他","","没有收到任何系统的消息"));
        list.add(new MessageEntity(R.drawable.message6,"UFO消息","","UFO出售、购买消息"));
        list.add(new MessageEntity(R.drawable.message7,"内容消息","","没有收到任何新消息"));
        list.add(new MessageEntity(R.drawable.message8,"在线客服","","服务时间:08:00-22:00"));

        RecyclerMessage_Adpater recyclerMessage_adpater = new RecyclerMessage_Adpater(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        messageRecycler.setAdapter(recyclerMessage_adpater);
        messageRecycler.setLayoutManager(linearLayoutManager);
        messageRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                messageRecycler.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                messageRecycler.loadMoreComplete();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        bind = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
