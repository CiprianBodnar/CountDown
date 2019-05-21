package com.example.countdown;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static   SharedPreferences  sharedPref = null;
    ImageView im,im2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im=(ImageView) findViewById(R.id.imageView);
        im2=(ImageView) findViewById(R.id.imageView2);


        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Game.class);
                startActivity(intent);
            }
        });

        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HighScore.class);
                SQLiteDatabase db = new myDBAdapter(v.getContext()).getReadableDatabase();

                String[] projection = {
                        myDBContract.Score._ID,
                        myDBContract.Score.COLUMN_SCORE
                };

                Cursor cursor = db.query(
                        myDBContract.Score.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null
                );

                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_action, menu);

        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
