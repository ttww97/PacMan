package com.example.zengtingwei.pacman;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

public class Player {
    Bitmap bitmap;
    float x = 100;
    float y = 100;

    public Player(Bitmap bitmap, float x, float y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    public void changeBitmap(Bitmap bit) {
        bitmap = Bitmap.createBitmap(bit);
    }

    public void updateX(float x) {
        this.x = x;
    }

    public void updateY(float y) {
        this.y = y;
    }

    public Bitmap changeBitmapSize(int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Log.e("width", "width:" + width);
        Log.e("height", "height" + height);

        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        bitmap.getWidth();
        bitmap.getHeight();
        Log.e("newWidth", "newWidth:" + bitmap.getWidth());
        Log.e("newHeight", "newHeight:" + bitmap.getHeight());
        return bitmap;
    }

    public Bitmap rotate(int angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);

        bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return bitmap;
    }
}
