package com.example.myapplication.mvp.ui.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchAcitvity extends AppCompatActivity {

    @BindView(R.id.search_activity_cancel)
    TextView searchActivityCancel;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_acitvity);
         bind = ButterKnife.bind(this);

        searchActivityCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
