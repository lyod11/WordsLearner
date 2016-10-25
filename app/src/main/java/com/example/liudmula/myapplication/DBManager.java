package com.example.liudmula.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by liudmula on 12.10.16.
 */

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    private String[] columns = new String[] {DatabaseHelper._ID, DatabaseHelper.WORD,
            DatabaseHelper.DESC, DatabaseHelper.PROGRESS};


    DBManager(Context context){
        this.context = context;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String word, String desc, Integer progress){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.WORD, word);
        contentValues.put(DatabaseHelper.DESC, desc);
        contentValues.put(DatabaseHelper.PROGRESS, progress);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor fetch() {
        //шапка таблиці

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns,
                null, null, null, null, null);
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }

    public int update(long _id, String word, String desc, Integer progress){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.WORD, word);
        contentValues.put(DatabaseHelper.DESC, desc);
        contentValues.put(DatabaseHelper.PROGRESS, progress);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + "=" +  _id, null);
        return i;
    }

    public int update(long _id, String word, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.WORD, word);
        contentValues.put(DatabaseHelper.DESC, desc);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + "=" +  _id, null);
        return i;
    }

    public int update(long _id, Integer progress){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.PROGRESS, progress);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + "=" +  _id, null);
        return i;
    }



    public void delete(long _id){
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" +_id, null);
    }

    public Cursor getWordsWithLowProgress(String numbers){
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, DatabaseHelper.PROGRESS, "3");
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }

    public Cursor getRandomRows(String limit){
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME + " ORDER BY RANDOM() LIMIT " + limit,
                new String[] { "*" }, null, null, null, null, null);
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }
}
