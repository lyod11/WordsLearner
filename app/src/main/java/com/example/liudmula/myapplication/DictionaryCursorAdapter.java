package com.example.liudmula.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by liudmula on 12.10.16.
 */

public class DictionaryCursorAdapter extends CursorAdapter {


    public DictionaryCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.dict_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView textView = (TextView) view.findViewById(R.id.tvWord);
        String string = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.WORD));
        textView.setText(string);

        textView = (TextView) view.findViewById(R.id.tvDescription);
        string = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DESC));
        textView.setText(string);

        textView = (TextView) view.findViewById(R.id.tvProgress);
        Integer integer = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PROGRESS));
        textView.setText(integer);


    }
}
