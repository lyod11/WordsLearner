package com.example.liudmula.myapplication.training;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.example.liudmula.myapplication.R;


public class TrainingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        int trainingFragmentIndex = getIntent().getIntExtra("training", -1);
        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle bundle = new Bundle();
        if(trainingFragmentIndex == 0){
            fragmentClass = WordTranslationFragment.class;
            bundle.putBoolean("type", getIntent().getBooleanExtra("type", true));
        }
        if(trainingFragmentIndex == 1){
            fragmentClass = WordTranslationFragment.class;
            bundle.putBoolean("type", getIntent().getBooleanExtra("type", false));
        }
        if(trainingFragmentIndex == 2){
            fragmentClass = TrainingTypingFragment.class;
        }
        if(trainingFragmentIndex == 3){
            fragmentClass = TrainingCardsFragment.class;
        }

        try {
            fragment = (Fragment)fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        FragmentManager fragmentManager = getFragmentManager();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.training_frame, fragment).commit();


    }

}