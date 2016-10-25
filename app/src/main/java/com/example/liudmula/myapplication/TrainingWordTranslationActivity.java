package com.example.liudmula.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import android.os.Handler;
import android.widget.TextView;


public class TrainingWordTranslationActivity extends Activity implements View.OnClickListener{
    Button btnTrans1, btnTrans2, btnTrans3, btnTrans4, btnTrans5, btnTrans6;
    TextView tvWord;
    Cursor learningWordsCursor, randomWordsCursor;
    DBManager dbManager;
    int indexWord, indexDesc;
    long id;
    Handler handler = new Handler();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_word_translation);

        tvWord = (TextView)findViewById(R.id.tv_trn1_word);

        btnTrans1 = (Button)findViewById(R.id.btn_trn1_w1);
        btnTrans1.setOnClickListener(this);
        btnTrans2 = (Button)findViewById(R.id.btn_trn1_w2);
        btnTrans2.setOnClickListener(this);
        btnTrans3 = (Button)findViewById(R.id.btn_trn1_w3);
        btnTrans3.setOnClickListener(this);
        btnTrans4 = (Button)findViewById(R.id.btn_trn1_w4);
        btnTrans4.setOnClickListener(this);
        btnTrans5 = (Button)findViewById(R.id.btn_trn1_w5);
        btnTrans5.setOnClickListener(this);
        btnTrans6 = (Button)findViewById(R.id.btn_trn1_w6);

        dbManager = new DBManager(getApplicationContext());
        dbManager.open();
        learningWordsCursor = dbManager.fetch();
        learningWordsCursor = dbManager.getWordsWithLowProgress("3");
        indexWord = learningWordsCursor.getColumnIndex(DatabaseHelper.WORD);
        indexDesc = learningWordsCursor.getColumnIndex(DatabaseHelper.DESC);
        setButtonsText();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_trn1_w1:
                if(btnTrans1.getText().toString() == learningWordsCursor.getString(indexDesc)){
                    updateProgress();


                }else{
                   /// btnTrans1.setBackgroundColor(getResources().getColor(R.color.colorRed));

                }
                break;

//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        btnTrans1.setBackgroundColor(getResources().getColor(R.color.colorGreen));
//                    }
//                }, 2000);
            case R.id.btn_trn1_w2:
                if(btnTrans2.getText().toString() == learningWordsCursor.getString(indexDesc)){
                    updateProgress();

                }else{
                    /// btnTrans1.setBackgroundColor(getResources().getColor(R.color.colorRed));

                }
                break;
            case R.id.btn_trn1_w3:
                if(btnTrans3.getText().toString() == learningWordsCursor.getString(indexDesc)){
                    updateProgress();

                }else{
                    /// btnTrans1.setBackgroundColor(getResources().getColor(R.color.colorRed));

                }
                break;
            case R.id.btn_trn1_w4:
                if(btnTrans4.getText().toString() == learningWordsCursor.getString(indexDesc)){
                    updateProgress();

                }else{
                    /// btnTrans1.setBackgroundColor(getResources().getColor(R.color.colorRed));

                }
                break;
            case R.id.btn_trn1_w5:
                if(btnTrans5.getText().toString() == learningWordsCursor.getString(indexDesc)){
                    updateProgress();

                }else{
                    /// btnTrans1.setBackgroundColor(getResources().getColor(R.color.colorRed));

                }
                break;
            case R.id.btn_trn1_w6:
                if(btnTrans6.getText().toString() == learningWordsCursor.getString(indexDesc)){
                    updateProgress();

                }else{
                    /// btnTrans1.setBackgroundColor(getResources().getColor(R.color.colorRed));

                }
                break;

        }
        buttonPressed();

    }


    public void buttonPressed(){
        if(learningWordsCursor.moveToNext()){
            setButtonsText();
        }else{
            learningWordsCursor.close();
        }
    }

    public void updateProgress(){
        id = learningWordsCursor.getLong(0);
        Integer progress = learningWordsCursor.getInt(learningWordsCursor.getColumnIndex(DatabaseHelper.PROGRESS));
        progress += 20;
        dbManager.update(id, progress);
    }


    public void setButtonsText(){
        ArrayList<String> randoms = new ArrayList<>();
        tvWord.setText(learningWordsCursor.getString(indexWord));
        randoms.add(learningWordsCursor.getString(indexDesc));
        randomWordsCursor = dbManager.getRandomRows("5");
        try {
            do {
               randoms.add(randomWordsCursor.getString(indexDesc)) ;
            }while(randomWordsCursor.moveToNext());
        } finally {
            randomWordsCursor.close();
        }
        long seed = System.nanoTime();
        Collections.shuffle(randoms, new Random(seed));
        btnTrans1.setText(randoms.get(0));
        btnTrans2.setText(randoms.get(1));
        btnTrans3.setText(randoms.get(2));
        btnTrans4.setText(randoms.get(3));
        btnTrans5.setText(randoms.get(4));
        btnTrans6.setText(randoms.get(5));


    }
}
