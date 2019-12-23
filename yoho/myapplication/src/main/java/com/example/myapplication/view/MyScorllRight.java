package com.example.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.example.myapplication.doman.IOnTouchEvent;

public class MyScorllRight extends View {

    Paint penBlack,penWhite,scoreBlack;
    int height,width,index = 0;
    IOnTouchEvent iOnTouchEvent;

    public MyScorllRight(Context context) {
        super(context);
    }

    public MyScorllRight(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        penBlack = new Paint();
        penBlack.setTextSize(30);
        penBlack.setStyle(Paint.Style.STROKE);
        penBlack.setAntiAlias(true);
        penBlack.setColor(Color.parseColor("#7E7C7C"));
        penBlack.setStrokeWidth(3);

        penWhite = new Paint();
        penWhite.setTextSize(30);
        penWhite.setStyle(Paint.Style.STROKE);
        penWhite.setAntiAlias(true);
        penWhite.setColor(Color.WHITE);
        penWhite.setStrokeWidth(3);

        scoreBlack = new Paint();
        scoreBlack.setTextSize(30);
        scoreBlack.setStyle(Paint.Style.FILL);
        scoreBlack.setAntiAlias(true);
        scoreBlack.setColor(Color.parseColor("#7E7C7C"));
        scoreBlack.setStrokeWidth(3);
    }

    public void setTouchEvent(IOnTouchEvent i){
        this.iOnTouchEvent = i;
    }

    public void setIndex(int index){
        this.index = index;
        invalidate();
    }

    public MyScorllRight(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = getHeight();
        width = getWidth();
        height = height / 27;
        int txtX = (width - 30) / 2;
        for (int i = 0; i < 27; i++) {
            char c = (char) (i+65);
            String txt = String.valueOf(c);
            int newHei = height * i + (height-30)/2+20;
            if(i == 26){
                txt = "0";
            }
            canvas.drawText(txt,txtX,newHei,penBlack);
        }
        char c = (char) (index+65);
        String txt = String.valueOf(c);
        if(index == 26){
            txt = "0";
        }
        int newHei = height * index + (height-30)/2+20;
        canvas.drawCircle(width/2-4,newHei-8,18,scoreBlack);
        canvas.drawText(txt,txtX,newHei,penWhite);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                float movey = event.getY();
                float movev = movey / height;
                index = (int) movev;
                invalidate();
                break;
            case MotionEvent.ACTION_DOWN:
                float y = event.getY();
                float v = y / height;
                index = (int) v;
                break;
            case MotionEvent.ACTION_UP:
                float upy = event.getY();
                float upv = upy / height;
                index = (int) upv;
                invalidate();
                break;
        }
        iOnTouchEvent.onTouchEvent(index);

        return true;
    }
}
