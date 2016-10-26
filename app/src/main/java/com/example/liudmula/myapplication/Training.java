package com.example.liudmula.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by liudmula on 25.10.16.
 */

public class Training {
    Cursor learningWordsCursor, randomWordsCursor;
    DBManager dbManager;
    int indexWord, indexDesc;
    long id;
    final Handler handler = new Handler();
    int delayTime = 2000;
    int correctAnswers = 0;
    Integer qnumber = 3;
    Context context;


    Training(Context context){

        this.context = context;
        dbManager = new DBManager(context);
        dbManager.open();
        learningWordsCursor = dbManager.fetch();
        learningWordsCursor = dbManager.getWordsWithLowProgress(qnumber.toString());
        indexWord = learningWordsCursor.getColumnIndex(DatabaseHelper.WORD);
        indexDesc = learningWordsCursor.getColumnIndex(DatabaseHelper.DESC);
    }


    public void updateProgress(){
        id = learningWordsCursor.getLong(0);
        Integer progress = learningWordsCursor.getInt(learningWordsCursor.getColumnIndex(DatabaseHelper.PROGRESS));
        progress += 20;
        dbManager.update(id, progress);
    }


    public void changeButtonColor(final boolean answer, final Button button, int time) {
        if (answer) {
            button.setBackgroundColor(context.getResources().getColor(R.color.colorGreen));
        } else {
            button.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
        }


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setBackgroundResource(android.R.drawable.btn_default);

            }
        }, time);
    }

    public ArrayList<String> getRandoms(String numbers, boolean word_translate) {
        ArrayList<String> randoms = new ArrayList<>();
        int index;
        randomWordsCursor = dbManager.getRandomRows(numbers);
        if(word_translate){
            index = indexDesc;
        }else{
            index = indexWord;
        }
        randoms.add(learningWordsCursor.getString(index));
        try {
            do {
                randoms.add(randomWordsCursor.getString(index));
            } while (randomWordsCursor.moveToNext());
        } finally {
                randomWordsCursor.close();
        }

        long seed = System.nanoTime();
        Collections.shuffle(randoms, new Random(seed));
        return randoms;
    }

    public boolean isNext(){
        if(learningWordsCursor.moveToNext()){
            return true;
        }else{
            learningWordsCursor.close();
            return false;
        }
    }
}
