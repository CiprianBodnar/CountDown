package com.example.countdown;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HighScore extends AppCompatActivity {

    private TextView scores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        scores = findViewById(R.id.scores);

        SQLiteDatabase db = new SQLConector(this).getReadableDatabase();

        String[] projection = {
                Create._ID,
                Create.NAME,
                Create.SCORE
        };

        Cursor cursor = db.query(
                Create.TABLE_NAME,
                projection,
                null,
                null,
                Create.SCORE,
                null,
                null
        );

        StringBuffer buffer = new StringBuffer();
        int i = 1;
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(Create.NAME));
            String score = cursor.getString(cursor.getColumnIndex(Create.SCORE));
            buffer.append(i + ". " + name + " " + score + "s \n");
            i ++;

        }

        scores.setText(buffer.toString());

    }
}

