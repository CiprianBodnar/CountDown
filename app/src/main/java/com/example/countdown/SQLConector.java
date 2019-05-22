package com.example.countdown;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class SQLConector extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myDatabase";
    private static final int DATABASE_VERSION = 5;

    public SQLConector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("SQLITEHelper", "Context reached");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create.CREATE_TABLE);
        Log.d("SQLITEHelper", "DB Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion > oldVersion) {
            db.execSQL("DELETE FROM " + Create.TABLE_NAME);
        }


    }
}