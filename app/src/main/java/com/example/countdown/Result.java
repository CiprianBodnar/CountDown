package com.example.countdown;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity implements View.OnClickListener {

    private TextView  scoreView;
    private Button tryAgain, menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        scoreView = findViewById(R.id.scoreText);
        tryAgain = findViewById(R.id.tryAgain);
        tryAgain.setOnClickListener(this);
        menuButton = findViewById(R.id.toMenu);
        menuButton.setOnClickListener(this);


        String time;
        time = getIntent().getStringExtra("SCORE");
        scoreView.setText(time);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tryAgain:
                Intent intent = new Intent(getApplicationContext(), Game.class);
                startActivity(intent);
                break;


            case R.id.toMenu:
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }


    }
}
