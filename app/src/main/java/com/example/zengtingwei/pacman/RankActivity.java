package com.example.zengtingwei.pacman;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class RankActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        Intent it = getIntent();
        String score = it.getStringExtra("score");
        TextView textView = findViewById(R.id.textView3);
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(30);
        System.out.println(score);
        textView.setText("Score: " + score);
    }

}
