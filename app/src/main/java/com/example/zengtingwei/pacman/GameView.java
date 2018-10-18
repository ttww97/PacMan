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
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class GameView extends View implements Runnable {

    ArrayList<GameOver> observers;
    private int speed = 1;
    private int count=0;
    Agent ghost1 = new Agent(1,17);
    Agent ghost2 = new Agent(26,19);
    Bitmap g1 = BitmapFactory.decodeResource(getResources(), R.drawable.ghost1);
    Bitmap g2 = BitmapFactory.decodeResource(getResources(), R.drawable.ghost2);
    Layout map;
    Directions direction = Directions.RIGHT;
    Player player;
    Handler timer;
    boolean checkWin;
    int score = 0;
    int beanNo = 100;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        map = new Layout();
        observers = new ArrayList<>();
        player = new Player(1,1);
        timer = new Handler();
        timer.postDelayed(this,10);
    }

    public void setMove(Directions direction){
        this.direction = direction;
        if (GameState.isMoveLegal(player.x,player.y,map,direction)){
            player.x = GameState.getNextX(player.x,direction);
            player.y = GameState.getNextY(player.y,direction);
            score += GameState.getScore(player.x,player.y,map);
            beanNo = GameState.getBeanNum(map);
        }
        this.invalidate();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onDraw(Canvas canvas) {
        drawMap(canvas);
        drawBean(canvas);
        player.drawPacman(canvas, direction);
        ghost1.drawGhost(canvas, g1);
        ghost2.drawGhost(canvas, g2);
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
                    canvas.drawCircle(j*0.036f*canvas.getWidth()+0.018f*canvas.getWidth(),i*0.036f*canvas.getWidth()+0.018f*canvas.getWidth(),0.018f*canvas.getWidth(),p1);
                }
                if(map.wall[i][j]==5){
                    canvas.drawCircle(j*0.036f*canvas.getWidth()+0.018f*canvas.getWidth(),i*0.036f*canvas.getWidth()+0.018f*canvas.getWidth(),0.018f*canvas.getWidth(),p2);
                }
            }
        }
    }
    private void drawScore(Canvas canvas){
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(0.05f*canvas.getWidth());
        canvas.drawText("Score："+String.valueOf(score),0.75f*canvas.getWidth(),0.85f*canvas.getWidth(),p);
    }

    private void drawMap(Canvas canvas){
        Paint p = new Paint();
        p.setColor(Color.GRAY);
        for(int i=0;i<map.wall.length;i++){
            for(int j=0;j<map.wall[i].length;j++){
                if(map.wall[i][j]==1){
                    canvas.drawRect(j*map.wallsize*canvas.getWidth(),i*map.wallsize*canvas.getWidth(),(j+1)*map.wallsize*canvas.getWidth(),(i+1)*map.wallsize*canvas.getWidth(),p);
                }
            }
        }
    }

    private void notifyGameOver() {
        for (GameOver o : observers) {
            o.gameOver();
        }
    }

    public void registerGameOver(GameOver gameOver) {
        observers.add(gameOver);
    }

    @Override
    public void run() {
        if(GameState.isGameOver(player.x,player.y,ghost1.x,ghost1.y,ghost2.x,ghost2.y,beanNo)) {
            checkWin = GameState.win;
            notifyGameOver();
        }else {
            count ++;
            if(count%(speed*10) == 0){
                Directions direction1 = GameState.generateDirection(ghost1.x,ghost1.y,player.x,player.y,map);
                ghost1.x = GameState.getNextX(ghost1.x,direction1);
                ghost1.y = GameState.getNextY(ghost1.y,direction1);
                Directions direction2 = GameState.generateDirection(ghost2.x,ghost2.y,player.x,player.y,map);
                ghost2.x = GameState.getNextX(ghost2.x,direction2);
                ghost2.y = GameState.getNextY(ghost2.y,direction2);
                player.startAngle = 10;
                player.sweepAngle = 340;
                this.invalidate();
            }
             else {
                player.startAngle = 45;
                player.sweepAngle = 270;
                this.invalidate();
            }
            this.postDelayed(this,100);
        }
    }

    public int getScore(){
        return score;
    }

    public boolean getWin(){
        return checkWin;
    }
}
