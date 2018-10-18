package com.example.zengtingwei.pacman;

import android.app.AppComponentFactory;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class GameView extends View implements Runnable {

    ArrayList<GameOver> observers;
    private int speed = 5;
    private int count=0;
    Bitmap pacman;
    Paint paint;
    float pacX = 50;
    float pacY = 50;
    float agent1X = 50;
    float agent1Y = 950;
    float agent2X = 1300;
    float agent2Y = 950;
    Layout map= new Layout();
    Directions direction = Directions.RIGHT;
    Agent ghost1;
    Agent ghost2;
    Player player;
    Handler timer;
    int score = 0;
    int beanNo = 100;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        observers = new ArrayList<>();
        pacman = BitmapFactory.decodeResource(getResources(), R.drawable.pacman);
        player = new Player(pacman, pacX, pacY);
        ghost1 = new Agent(agent1X,agent1Y);
        ghost2 = new Agent(agent2X,agent2Y);
        timer = new Handler();
        timer.postDelayed(this,10);
    }

    public void setMove(Directions direction){
        this.direction = direction;
        int idx_j = (int)GameState.getNextX(pacX,direction)/50;
        int idx_i = (int)GameState.getNextY(pacY,direction)/50;
        if (GameState.isMoveLegal(pacX,pacY,map,direction)){
            pacX = GameState.getNextX(pacX,direction);
            pacY = GameState.getNextY(pacY,direction);
            score += GameState.getScore(idx_i,idx_j,map);
            beanNo = GameState.getBeanNum(map);
        }
        this.invalidate();
    }


    @Override
    public void onDraw(Canvas canvas) {
        drawMap(canvas);
        drawBean(canvas);
        drawPacman(canvas);
        drawAgent(canvas);
        drawScore(canvas);
    }
    private void drawBean(Canvas canvas){
        Paint p1 = new Paint();
        Paint p2 = new Paint();
        p1.setColor(Color.GREEN);
        p2.setColor(Color.RED);
        for(int i=0;i<map.wall.length;i++){
            for(int j=0;j<map.wall[i].length;j++){
                if(map.wall[i][j]==2){
                    canvas.drawCircle(j*50+25,i*50+25,25,p1);
                }
                if(map.wall[i][j]==5){
                    canvas.drawCircle(j*50+25,i*50+25,25,p2);
                }
            }
        }
    }
    private void drawScore(Canvas canvas){
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(100);
        canvas.drawText("Score："+String.valueOf(score),800,1200,p);
    }
    private void drawPacman(Canvas canvas){
        Paint p = new Paint();
        pacman = changeBitmapSize(pacman, 50, 50);
        canvas.drawBitmap(pacman, pacX,pacY,p);

    }
    private void drawAgent(Canvas canvas){
        Paint ghost = new Paint();
        Bitmap ghos1 = BitmapFactory.decodeResource(getResources(), R.drawable.ghost1);
        Bitmap ghos2 = BitmapFactory.decodeResource(getResources(), R.drawable.ghost2);
        ghos1 = changeBitmapSize(ghos1, 50,50);
        ghos2 = changeBitmapSize(ghos2, 50,50);
        canvas.drawBitmap(ghos1, agent1X, agent1Y, ghost);
        canvas.drawBitmap(ghos2, agent2X, agent2Y, ghost);
    }

    private void drawMap(Canvas canvas){
        Paint p = new Paint();
        p.setColor(Color.GRAY);
        for(int i=0;i<map.wall.length;i++){
            for(int j=0;j<map.wall[i].length;j++){
                if(map.wall[i][j]==1){
                    canvas.drawRect(j*map.wallsize,i*map.wallsize,(j+1)*map.wallsize,(i+1)*map.wallsize,p);
                }
            }
        }
    }

    private void notifyGameOver() {
        for (GameOver o : observers) o.gameOver();
    }

    public void registerGameOver(GameOver gameOver) {
        observers.add(gameOver);
    }

    @Override
    public void run() {
        if(GameState.isGameOver(pacX,pacY,agent1X,agent1Y,beanNo)) {
            notifyGameOver();
        }
        if(!GameState.isGameOver(pacX,pacY,agent1X,agent1Y,beanNo)) {
            count ++;
            if(count%speed == 0){
                Directions direction1 = GameState.generateDirection(agent1X,agent1Y,pacX,pacY,map);
                agent1X = GameState.getNextX(agent1X,direction1);
                agent1Y = GameState.getNextY(agent1Y,direction1);
                Directions direction2 = GameState.generateDirection(agent2X,agent2Y,pacX,pacY,map);
                agent2X = GameState.getNextX(agent2X,direction2);
                agent2Y = GameState.getNextY(agent2Y,direction2);
                this.invalidate();
            }
            this.postDelayed(this,100);
    }}

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
}
