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

public class Player {
    float x;
    float y;
    Directions direction = Directions.RIGHT;
    int startAngle = 45;
    int sweepAngle = 270;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public void moveUp() {
        y -= 50;
    }

    public void moveRight() {
        x += 50;
    }

    public void moveDown() {
        y += 50;
    }

    public void moveLeft(){
        x -= 50;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void drawPacman(Canvas canvas, Directions direction){
        int rotate = 0;
        switch (direction){
            case RIGHT:rotate = 0;break;
            case LEFT:rotate = 180;break;
            case UP:rotate = -90;break;
            case DOWN:rotate = 90;break;
        }

        Paint pacman = new Paint();
        pacman.setColor(Color.YELLOW);
        canvas.drawArc(x, y, x+50,y+50, startAngle + rotate, sweepAngle, true, pacman);
    }
}
