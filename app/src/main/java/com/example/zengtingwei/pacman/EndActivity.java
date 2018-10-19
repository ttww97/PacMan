package com.example.zengtingwei.pacman;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

//written by Zhiyuan Xu, Tingwei Zeng
public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Intent it = getIntent();
        String score = it.getStringExtra("score");
        TextView textView = findViewById(R.id.textView);
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(30);
        textView.setText("Score: " + score);

        TextView textView1 = findViewById(R.id.textView2);
        textView1.setTextColor(Color.BLACK);
        textView1.setBackgroundColor(Color.WHITE);
        textView1.setTextSize(30);
        String win = it.getStringExtra("win?");
        textView1.setText(win);
    }

    public void menu(View view){
        Log.d("Menu", "Return to menu");
        Intent it = new Intent(this, WelcomeActivity.class);
        startActivity(it);
        this.finish();
    }

    public void exit(View view) {
        this.finish();
    }
}
