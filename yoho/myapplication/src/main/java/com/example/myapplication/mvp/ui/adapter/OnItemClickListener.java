package com.example.myapplication.mvp.ui.adapter;

import android.widget.TextView;

import java.util.ArrayList;

public interface OnItemClickListener {

    void onclick(int i, TextView textView, ArrayList<Boolean> booleans);
}
