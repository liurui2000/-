package com.example.myapplication.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SerMesageCenterActivity extends AppCompatActivity {

    @BindView(R.id.serMess_back)
    ImageView serMessBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ser_mesage_center);
        ButterKnife.bind(this);

        serMessBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
