package com.example.zengtingwei.pacman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class WelcomeView extends View {

    Bitmap myImage;

    public WelcomeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        myImage = BitmapFactory.decodeResource(getResources(), R.drawable.pacback);
    }

    public Bitmap changeBitmapSize(Bitmap bit, int w, int h){
        int width = bit.getWidth();
        int height = bit.getHeight();
        Log.e("width", "width:" + width);
        Log.e("height", "height" + height);

        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float) newWidth)/width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        bit = Bitmap.createBitmap(bit,0,0, width, height, matrix, true);
        bit.getWidth();
        bit.getHeight();
        Log.e("newWidth", "newWidth:" + bit.getWidth());
        Log.e("newHeight", "newHeight:" + bit.getHeight());
        return bit;
    }

    @Override
    public void onDraw(Canvas canvas){
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        myImage = changeBitmapSize(myImage, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(myImage,0,0,p);
    }
}
