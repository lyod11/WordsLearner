package com.example.liudmula.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liudmula on 11.10.16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "WORDS";

    public static final String _ID = "_id";
    public static final String WORD = "word";
    public static final String DESC = "description";
    public static final String PROGRESS = "progress";

    static final String DB_NAME = "ENGLISH_RUSSIAN.DB";

    static final int DB_VERSION = 1;

    private static final String  CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            WORD + " TEXT NOT NULL, " +
            DESC + " TEXT, " +
            PROGRESS + " INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }
}
