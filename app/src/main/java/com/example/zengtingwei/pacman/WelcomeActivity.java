package com.example.zengtingwei.pacman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class WelcomeActivity extends AppCompatActivity implements OnMenuItemClickListener{

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

    public void selectDifficulty(View view) {
        PopupMenu menu = new PopupMenu(this,view);
        getMenuInflater().inflate(R.menu.difficulty_menu, menu.getMenu());
        menu.setOnMenuItemClickListener(this);
        menu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.easy:
                GameView.speed =10;
                break;
            case R.id.moderate:
                GameView.speed =5;
                break;
            case R.id.hard:
                GameView.speed =2;
                break;
            default:
                break;
        }
        return false;
    }
}
