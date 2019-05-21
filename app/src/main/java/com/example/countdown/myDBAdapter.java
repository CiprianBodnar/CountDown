package com.example.countdown;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class myDBAdapter extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "projectDatabase";
    private static final int DATABASE_VERSION = 4;

    private static myDBAdapter instance;


    public myDBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("SQLITEHelper", "Context reached");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(myDBContract.Score.CREATE_TABLE);
        Log.d("SQLITEHelper", "DB Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion > oldVersion) {
            db.execSQL("DELETE FROM " + myDBContract.Score.TABLE_NAME);
        }


    }
}