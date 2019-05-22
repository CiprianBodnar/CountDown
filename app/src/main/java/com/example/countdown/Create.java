package com.example.countdown;

import android.provider.BaseColumns;

class Create implements BaseColumns {
    public static final String TABLE_NAME = "leaderboard";
    public static final String NAME = "name";
    public static final String SCORE = "score";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            SCORE + " TEXT" + ")";
}