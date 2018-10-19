package com.example.zengtingwei.pacman;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
//written by Tingwei Zeng,modified by Zhiyuan Xu

public class Player {
    int x;
    int y;
    int startAngle = 45;
    int sweepAngle = 270;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    //draw pac man
    public void drawPacman(Canvas canvas, Directions direction){
        int rotate = 0;
        switch (direction){
            case RIGHT:rotate = 0;break;
            case LEFT:rotate = 180;break;
            case UP:rotate = -90;break;
            case DOWN:rotate = 90;break;
        }

        Paint pacman = new Paint();
        pacman.setColor(Color.rgb(255,212,58));
        canvas.drawArc(x*0.036f*canvas.getWidth(), y*0.036f*canvas.getWidth(), x*0.036f*canvas.getWidth()+0.036f*canvas.getWidth(),y*0.036f*canvas.getWidth()+0.036f*canvas.getWidth(), startAngle + rotate, sweepAngle, true, pacman);
    }
}
