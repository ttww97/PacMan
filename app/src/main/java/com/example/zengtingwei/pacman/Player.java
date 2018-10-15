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
}
