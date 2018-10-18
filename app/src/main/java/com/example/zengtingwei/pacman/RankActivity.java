package com.example.zengtingwei.pacman;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class RankActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);;
        DataStore bespoke = new DataStore();
        System.out.println("dsfs");
        bespoke.saveData(Integer.toString(GameView.score_rank),"rank.txt");
        System.out.println("dsfafdsafdf");
        ArrayList<String> scores = bespoke.loadData("rank.txt");
        int[] s = sortRank(scores);
        String show="";
        for(int i = 0;i<10;i++){
            show += s[i]+"\n";
        }

        TextView textView = findViewById(R.id.textView3);
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(30);
        System.out.println(show);
        textView.setText("Score: " + show);
    }

    public int[] sortRank(ArrayList<String> scores){
        int size = scores.size();
        int[] s= new int[size];
        for(int i= 0;i<size;i++){
            s[i]=(Integer.parseInt(scores.get(i)));
        }
        for(int i=0;i<size-1;i++){
            for(int j=i+1;j<size;j++){
                if(s[i]<s[j]){
                    int t=s[i];
                    s[i] = s[j];
                    s[j] = t;
                }
            }
        }
        return s;
    }

}
