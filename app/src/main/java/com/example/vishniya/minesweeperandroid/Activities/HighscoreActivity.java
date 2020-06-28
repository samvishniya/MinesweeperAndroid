package com.example.vishniya.minesweeperandroid.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vishniya.minesweeperandroid.MinesweeperGameActivity;
import com.example.vishniya.minesweeperandroid.R;

public class HighscoreActivity extends AppCompatActivity {

    private int newScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        Intent intent = getIntent();
        newScore = intent.getIntExtra(MinesweeperGameActivity.EXTRA_SCORE, 1);



    }
}
