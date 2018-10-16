package com.example.zengtingwei.pacman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Intent it = getIntent();
    }

    public void restart(View view){
        Log.d("Restart", "Game restart");
        Intent it = new Intent(this, GameActivity.class);
        startActivity(it);
    }

    public void menu(View view){
        Log.d("Menu", "Return to menu");
        Intent it = new Intent(this, WelcomeActivity.class);
        startActivity(it);
    }
}
