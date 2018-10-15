package com.example.zengtingwei.pacman;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class GameActivity extends AppCompatActivity implements GameOver {
    Directions directions;
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = findViewById(R.id.gameView);
        gameView.registerGameOver(this);
    }

    @Override
    public void gameOver() {
        Log.d("game","Game end");
        Intent intent = new Intent(this, EndActivity.class);
        startActivity(intent);
    }

    public void goUp(View view){
        directions = Directions.UP;
        gameView.setMove(directions);
    }

    public void goRight(View view){
        directions = Directions.RIGHT;
        gameView.setMove(directions);
    }

    public void goDown(View view){
        directions = Directions.DOWN;
        gameView.setMove(directions);
    }

    public void goLeft(View view){
        directions = Directions.LEFT;
        gameView.setMove(directions);
    }

}
