package com.example.zengtingwei.pacman;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class GameView extends View implements Runnable {

    ArrayList<GameOver> observers;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        observers = new ArrayList<>();
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
}
