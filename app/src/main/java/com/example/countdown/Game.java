package com.example.countdown;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private ProgressBar progressBar;
    private TextView textView;
    private long startTime = 5 * 1000;
    private final long intervale = 1;
    private boolean timerHasStarted = false;
    private CountDownTimer countDownTimer;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.seconds);
        button.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        countDownTimer = new MyCountDownTimer(startTime,intervale);

        ((MyCountDownTimer) countDownTimer).update();
    }

    void gameOver(String time){
        Intent intent = new Intent(getApplicationContext(), Result.class);
        intent.putExtra("SCORE",time);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button:
               countDownStarter();

            default:
                break;
        }

    }

    private void countDownStarter(){
        if(!timerHasStarted){
            progressBar.setVisibility(View.VISIBLE);
            countDownTimer.start();
            timerHasStarted = true;
            button.setText("Stop");
        }
        else{
            timerHasStarted = false;
            countDownTimer  .cancel();
            gameOver(String.valueOf(textView.getText()));
        }
    }



    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            textView.setText("Time's up!");
            gameOver(String.valueOf(textView.getText()));
        }

        @Override
        public void onTick(long millisUntilFinished) {

            startTime = millisUntilFinished;
            update();

        }

        private void update() {
            int minutes = (int) startTime / 60000;
            int seconds = (int) startTime % 60000 / 1000;

            String timeLeftText;

            timeLeftText = "" + minutes;
            timeLeftText += ":";


            timeLeftText += "0" + seconds;
            timeLeftText += ".";

            timeLeftText += startTime % 1000;

            if (seconds < 2) textView.setVisibility(View.GONE);

            textView.setText(timeLeftText);
        }
    }
}
