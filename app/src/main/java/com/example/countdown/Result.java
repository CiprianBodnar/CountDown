package com.example.countdown;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends AppCompatActivity implements View.OnClickListener {

    private TextView  scoreView;
    private Button tryAgain, menuButton, save;
    private ImageView whatsup, gmail;
    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        scoreView = findViewById(R.id.scoreText);
        tryAgain = findViewById(R.id.tryAgain);
        tryAgain.setOnClickListener(this);
        menuButton = findViewById(R.id.toMenu);
        menuButton.setOnClickListener(this);
        whatsup = findViewById(R.id.whatsup);
        gmail = findViewById(R.id.gmail);
        whatsup.setOnClickListener(this);
        gmail.setOnClickListener(this);
        save = findViewById(R.id.save);
        save.setOnClickListener(this);

        Intent i = getIntent();
        time = i.getStringExtra("SCORE");
        scoreView.setText(time);


    }

    @Override
    public void onClick(View v) {
        String text = "Hey there, can you beat my " + String.valueOf(time) + " at Count Down? Let's see";
        switch (v.getId()){

            case R.id.tryAgain:
                Intent intent = new Intent(getApplicationContext(), Game.class);
                startActivity(intent);
                break;


            case R.id.toMenu:
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;

            case R.id.whatsup:
                PackageManager pm=getPackageManager();
                Intent waIntent = new Intent(Intent.ACTION_SEND);
                waIntent.setType("text/plain");


                try {
                    PackageInfo info=pm.getPackageInfo("com.whatsapp",
                            PackageManager.GET_META_DATA);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                waIntent.setPackage("com.whatsapp");
                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(waIntent, "Share with"));
                break;
            case R.id.gmail:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_TEXT,text);
                email.putExtra(Intent.EXTRA_SUBJECT,"Can you beat my "+String.valueOf(time) + " time? I dare you");
                email.setType("message/rfc822");
                startActivity(email.createChooser(email,"choose an email client"));
                break;
            case R.id.save:
               dialogHandler();
               break;
            default:
                break;
        }


    }

    void dialogHandler(){
        AlertDialog.Builder dialog  = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_layout,null);
        final EditText editText = view.findViewById(R.id.user_name);

        dialog.setView(view).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!editText.getText().toString().isEmpty()) {
                    Save(editText.getText().toString(), time);
                }
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }


    private void Save(String userName, String userScore){
        SQLiteDatabase db = new myDBAdapter(this).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(myDBContract.Score.COLUMN_NAME, userName);
        values.put(myDBContract.Score.COLUMN_SCORE, userScore);
        long rowId = db.insert(myDBContract.Score.TABLE_NAME, null, values);
    }


}
