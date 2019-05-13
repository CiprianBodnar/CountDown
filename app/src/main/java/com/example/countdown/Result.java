package com.example.countdown;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity implements View.OnClickListener {

    private TextView highScoreView, scoreView;
    private Button tryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        highScoreView = findViewById(R.id.highScoreText);
        scoreView = findViewById(R.id.scoreText);
        tryAgain = findViewById(R.id.tryAgain);
        tryAgain.setOnClickListener(this);

        int score=0, highScore=0;

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

        highScore = settings.getInt("HIGH_SCORE",0);
        score = getIntent().getIntExtra("SCORE",0);

        scoreView.setText(String.valueOf(score));
        if(score>highScore){

            highScore = score;
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE",score);
            editor.commit();

        }
        else {
            highScoreView.setText("High Score:" + highScore);
        }

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getApplicationContext(), Game.class);
        startActivity(intent);
    }
}
