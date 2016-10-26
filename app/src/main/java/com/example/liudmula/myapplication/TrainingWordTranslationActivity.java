package com.example.liudmula.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.Button;
import android.os.Handler;
import android.widget.TextView;


public class TrainingWordTranslationActivity extends Activity {
    Button btnTrans1, btnTrans2, btnTrans3, btnTrans4, btnTrans5, btnTrans6;
    TextView tvWord;
    final Handler handler = new Handler();
    int delayTime = 2000;
    int correctAnswers = 0;
    Integer qnumber = 3;
    Training training;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_word_translation);
        int trainingFragmentIndex = getIntent().getIntExtra("training", -1);
        Fragment fragment = null;
        Class fragmentClass = null;
        if(trainingFragmentIndex == 0){
            fragmentClass = WordTranslationFragment.class;
        }

        try {
            fragment = (Fragment)fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.training_frame, fragment).commit();

    }

}