package com.example.zengtingwei.pacman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

public class Agent {
    Bitmap bitmap;
    float x;
    float y;

    public Agent(float x, float y){
        this.x = x;
        this.y = y;
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

    public void drawGhost(Canvas canvas, Bitmap bitmap){
        Paint ghost = new Paint();
        bitmap = changeBitmapSize(bitmap, 50,50);
        canvas.drawBitmap(bitmap, x, y, ghost);
    }

}
