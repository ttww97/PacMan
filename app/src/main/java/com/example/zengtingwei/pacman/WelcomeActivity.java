package com.example.zengtingwei.pacman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
        this.finish();
    }

    public void showHelp(View view) {
        String text = "1.You can press the direction button to control your move.\n" +
                      "2.You cannot encounter both red and blue goast,otherwise you loose\n" +
                      "3.Red bean denotes 5 point, green bean denotes 1 point\n";
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
