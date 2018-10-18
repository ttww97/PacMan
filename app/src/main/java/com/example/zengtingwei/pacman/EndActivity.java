package com.example.zengtingwei.pacman;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Intent it = getIntent();
        String score = it.getStringExtra("score");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(30);
        textView.setText("Score: " + score);

        TextView textView1 = (TextView) findViewById(R.id.textView2);
        textView1.setTextColor(Color.WHITE);
        textView1.setTextSize(30);
        String win = it.getStringExtra("win?");
        textView1.setText(win);
    }

    public void menu(View view){
        Log.d("Menu", "Return to menu");
        Intent it = new Intent(this, WelcomeActivity.class);
        startActivity(it);
    }

    public void exit(View view) {
        this.finish();
    }
}
