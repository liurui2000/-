package com.example.myapplication.mvp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class SetActivity extends AppCompatActivity {
    Button tuichu;
    ImageView back;
    TextView tv_mine_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        tuichu=findViewById(R.id.set_tuichu_activity);
        back=findViewById(R.id.set_back_activity);
        tv_mine_message = findViewById(R.id.tv_mine_message);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences islogin =  getSharedPreferences("islogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = islogin.edit();
                edit.putBoolean("islog", false);
                edit.commit();
                Intent intent = new Intent(SetActivity.this, MainActivity.class);
                intent.putExtra("ispage",4);
                startActivity(intent);
            }
        });
        tv_mine_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SetActivity.this,ChangeUserActivity.class));
            }
        });
    }
}
