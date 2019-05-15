package com.example.countdown;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;

public class Game extends AppCompatActivity implements View.OnClickListener {
    public int counter=0;
    private Button button;
    long actualSec;
    private TextView textView, scoreUpdateView;
    private ImageView paused;
    private final long startTime = 6 * 1000;
    private final long intervale = 1 * 1000;
    private boolean timerHasStarted = false, pauseFlag = false;
    private CountDownTimer countDownTimer, save;
    private Timer timer = new Timer();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.seconds);
        scoreUpdateView = findViewById(R.id.ScoreUpdateView);
        button.setOnClickListener(this);
        paused = findViewById(R.id.pauseButton);
        paused.setOnClickListener(this);

        String result = textView.getText() + String.valueOf(startTime/1000);
        textView.setText(result);
        countDownTimer = new MyCountDownTimer(startTime,intervale);

    }

    void gameOver(int score){
        Intent intent = new Intent(getApplicationContext(), Result.class);
        intent.putExtra("SCORE",score);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button:
               countDownStarter();
            case R.id.pauseButton:
                pauseGame(paused);

            default:
                break;
        }

    }

    private void countDownStarter(){
        if(!timerHasStarted){
            countDownTimer.start();
            timerHasStarted = true;
            button.setText("Stop");
        }
        else{

            //int sec = Integer.parseInt (textView.getText().toString());
            if(actualSec == 0){

                counter ++;
                scoreUpdateView.setText("Score: " + String.valueOf(counter));
                countDownTimer.cancel();
                countDownTimer.start();
                timerHasStarted = true;
            }
            else{
                countDownTimer  .cancel();
                gameOver(counter);
            }
        }
    }

    public void pauseGame(View v){


        if(pauseFlag == false){
            pauseFlag = true;
           // countDownTimer = save;

        }
        else{
            pauseFlag = false;
           // save = countDownTimer;
            countDownTimer.cancel();

        }
    }


    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            textView.setText("Time's up!");
            gameOver(counter);
        }

        @Override
        public void onTick(long millisUntilFinished) {


            if(millisUntilFinished/1000 >=2 && millisUntilFinished/1000<=6) {
                textView.setText("" + millisUntilFinished / 1000);
                actualSec = millisUntilFinished/1000;
            }
            else {
                textView.setText("");
                actualSec = millisUntilFinished/1000;
            }

        }
    }
}
