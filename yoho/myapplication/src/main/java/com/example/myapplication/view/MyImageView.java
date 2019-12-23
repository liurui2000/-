package com.example.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.example.myapplication.R;

@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView {
    Matrix matrix = new Matrix();
    public Bitmap bitmap;
    int image;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public MyImageView(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.login_piece_icon1);
        invalidate();
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.login_piece_icon1);
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,matrix,paint);
    }

    public void rotate(){
        matrix.preRotate(90,bitmap.getWidth()/2,bitmap.getHeight()/2);
        invalidate();
    }
}
