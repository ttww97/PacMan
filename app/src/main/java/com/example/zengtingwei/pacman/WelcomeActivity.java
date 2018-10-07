package com.example.zengtingwei.pacman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void startGame(View view){
        Log.d("game", "Game start");
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
