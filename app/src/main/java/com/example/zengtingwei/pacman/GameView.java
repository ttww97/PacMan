package com.example.zengtingwei.pacman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class GameView extends View implements Runnable, View.OnTouchListener {

    ArrayList<GameOver> observers;
    Bitmap pacman;
    Bitmap pacman1, pacman2, pacman3, pacTemp;
    Canvas c;
    Paint paint;
    float pacX = 100;
    float pacY = 100;
    String s = "Left";

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);
        observers = new ArrayList<>();
        pacman = BitmapFactory.decodeResource(getResources(), R.drawable.pacman);
    }

    @Override
    public void onDraw(Canvas canvas){
        Paint p = new Paint();
        pacman = changeBitmapSize(pacman, 75,75);
        Bitmap pacman1 = rotate(pacman,90);
        Bitmap pacman2 = rotate(pacman,180);
        Bitmap pacman3 = rotate(pacman, 270);
        if (s == "Right")
            canvas.drawBitmap(pacman, pacX,pacY,p);
        else if (s == "Up")
            canvas.drawBitmap(pacman1,pacX,pacY,p);
        else if (s == "Left")
            canvas.drawBitmap(pacman2, pacX,pacY,p);
        else
            canvas.drawBitmap(pacman3, pacX,pacY,p);
        }

    private void notifyGameOver() {
        for (GameOver o : observers) o.gameOver();
    }

    public void registerGameOver(GameOver gameOver) {
        observers.add(gameOver);
    }

    @Override
    public void run() {
        if(true) {
            notifyGameOver();
        }
    }

    public Bitmap rotate(Bitmap bitmap, int angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);

        bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return bitmap;
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
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            if(motionEvent.getX() < 300){
                pacX -= 50;
                s = "Left";
            }
            else if(motionEvent.getX() > 800){
                pacX += 50;
                s = "Right";
            }
            else if (motionEvent.getY() > pacY){
                pacY += 50;
                s = "Up";
            }
            else{
                pacY -= 50;
                s = "Down";
            }
        }
        this.invalidate();
        return true;
    }
}
