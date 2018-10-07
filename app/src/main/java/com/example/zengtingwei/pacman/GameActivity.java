package com.example.zengtingwei.pacman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class GameActivity extends AppCompatActivity implements GameOver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GameView gameView = findViewById(R.id.gameView);
        gameView.registerGameOver(this);
    }

    @Override
    public void gameOver() {
        Log.d("game","Game end");
        Intent intent = new Intent(this, EndActivity.class);
        startActivity(intent);
    }
}
