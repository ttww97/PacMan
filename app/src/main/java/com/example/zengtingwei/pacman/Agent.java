package com.example.zengtingwei.pacman;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

public class Agent {
    Bitmap bitmap;
    int x;
    int y;

    public Agent(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Bitmap changeBitmapSize(Bitmap bit, float w, float h){
        int width = bit.getWidth();
        int height = bit.getHeight();
        Log.e("width", "width:" + width);
        Log.e("height", "height" + height);

        float newWidth = w;
        float newHeight = h;

        float scaleWidth = newWidth / width;
        float scaleHeight = newHeight / height;

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
        bitmap = changeBitmapSize(bitmap, 0.036f*canvas.getWidth(),0.036f*canvas.getWidth());
        canvas.drawBitmap(bitmap, x*0.036f*canvas.getWidth(), y*0.036f*canvas.getWidth(), ghost);
    }

    public Bitmap rotate(Bitmap bitmap, int angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);

        bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return bitmap;
    }

}
